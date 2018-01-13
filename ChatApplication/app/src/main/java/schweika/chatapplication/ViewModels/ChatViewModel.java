package schweika.chatapplication.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import schweika.chatapplication.BR;
import schweika.chatapplication.GenericRecyclerViewAdapter;
import schweika.chatapplication.Models.API.Message;
import schweika.chatapplication.Models.API.Participant;
import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Repositories.RXMessageRepository;
import schweika.chatapplication.Repositories.RXParticipantRepository;
import schweika.chatapplication.TokenSingleton;

public class ChatViewModel extends BaseObservable
{
    public Room room;
    public ArrayList<Participant> participants = new ArrayList<>();
    public ArrayList<Message> messages = new ArrayList<>();
    private String messageInput = "";
    private GenericRecyclerViewAdapter<MessageViewModel> adapter;
    private User curentUser = TokenSingleton.getInstance().getUser();

    private RXMessageRepository rxMessageRepository = new RXMessageRepository(TokenSingleton.getInstance().getToken());
    private RXParticipantRepository rxParticipantRepository = new RXParticipantRepository(TokenSingleton.getInstance().getToken());

    public ChatViewModel(Room room, GenericRecyclerViewAdapter<MessageViewModel> adapter)
    {
        this.room = room;
        this.adapter = adapter;

        //loadParticipants();
        //loadMessages();
        initialize();
    }

    /*private void loadMessages()
    {
        rxMessageRepository.getByRoomID(room.id,room.utcCreationDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(messages1 ->
                {
                    this.setMessages(new ArrayList<>(messages1));
                }, throwable ->
                {

                });
    }

    private void loadParticipants()
    {
        rxParticipantRepository.getByRoomId(room.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(participants1 ->
                {
                    this.participants = new ArrayList<>(participants1);
                }, throwable ->
                {

                });
    }*/

    private void initialize()
    {
        rxParticipantRepository.getByRoomId(room.id)
                .flatMap(participants -> rxMessageRepository.getByRoomID(room.id,room.utcCreationDate), (participants,messages) -> {return new Pair<List<Participant>,List<Message>>(participants,messages);})
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listListPair ->
                {
                    this.participants = new ArrayList<>(listListPair.first);
                    this.setMessages(new ArrayList<>(listListPair.second));

                    Timer timer = new Timer();
                    timer.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            checkForMessages();
                        }
                    },0,5000);
                });
    }

    private void setMessages(ArrayList<Message> messages)
    {
        this.messages = messages;

        ArrayList<MessageViewModel> messageViewModels = new ArrayList<>();

        for (Message message : this.messages)
        {
            Participant participant = getParticipantByUserId(message.idUser);
            MessageViewModel messageViewModel = new MessageViewModel(message,participant);
            messageViewModels.add(messageViewModel);
        }

        this.adapter.setItems(messageViewModels);
    }

    private void addMessages(List<Message> messages)
    {
        for (Message message : messages)
        {
            this.messages.add(message);

            Participant participant = getParticipantByUserId(message.idUser);
            MessageViewModel messageViewModel = new MessageViewModel(message,participant);
            this.adapter.addItem(messageViewModel);
        }
    }

    private Participant getParticipantByUserId(long id)
    {
        for (Participant item : this.participants)
        {
            if (item.idUser == id)
            {
                return item;
            }
        }

        return null;
    }

    private void checkForMessages()
    {
        Date date;

        if (this.messages.size() > 0)
            date = getLatestMessage().utcServerReceived;
        else
            date = room.utcCreationDate;

        rxMessageRepository.getByRoomID(room.id,date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(messages1 ->
                {
                    if (messages1.size() > 0)
                        addMessages(messages1);
                });
    }

    private Message getLatestMessage()
    {
        return this.messages.get(this.messages.size() - 1);
    }

    public void send()
    {
        if (!messageInput.equals(""))
        {
            Message messageModel = new Message();
            messageModel.idUser = curentUser.id;
            messageModel.idRoom = room.id;
            messageModel.content = messageInput;
            messageModel.utcSend = new Date();

            rxMessageRepository.create(messageModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() ->
                    {
                        setMessageInput("");
                    });
        }
    }

    @Bindable
    public String getMessageInput()
    {
        return this.messageInput;
    }

    public void setMessageInput(String value)
    {
        this.messageInput = value;
        notifyPropertyChanged(BR.messageInput);
    }
}
