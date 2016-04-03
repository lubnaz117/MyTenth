package com.example.lubna.mytenth;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Random;

public class myList2 extends ListActivity {

    // TODO: change this to your own Firebase URL
    private static final String FIREBASE_URL = "https://boiling-fire-1372.firebaseio.com/";

    private String mUsername;
    private Firebase mFirebaseRef;
    private ValueEventListener mConnectedListener;
    private ChatListAdapter mChatListAdapter;
    private int listNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list2);
        // Make sure we have a mUsername
        setupUsername();

        setTitle("Chatting as " + mUsername);

        // Setup our Firebase mFirebaseRef
        mFirebaseRef = new Firebase(FIREBASE_URL).child("chat");

        // Setup our input methods. Enter key on the keyboard or pushing the send button
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        inputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_NULL && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    sendMessage();
                }
                return true;
            }
        });

        findViewById(R.id.sendButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Setup our view and list adapter. Ensure it scrolls to the bottom as data changes
        final ListView listView = getListView();
        // Tell our list adapter that we only want 50 messages at a time
        mChatListAdapter = new ChatListAdapter(mFirebaseRef.limit(50), this, R.layout.chat_message, mUsername);
        listView.setAdapter(mChatListAdapter);
        mChatListAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listView.setSelection(mChatListAdapter.getCount() - 1);
            }
        });

        // Finally, a little indication of connection status
        mConnectedListener = mFirebaseRef.getRoot().child(".info/connected").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean connected = (Boolean) dataSnapshot.getValue();
                if (connected) {
                    Toast.makeText(myList2.this, "Connected to Firebase", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(myList2.this, "Disconnected from Firebase", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                // No-op
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        mFirebaseRef.getRoot().child(".info/connected").removeEventListener(mConnectedListener);
        mChatListAdapter.cleanup();
    }

    private void setupUsername() {
        SharedPreferences prefs = getApplication().getSharedPreferences("ChatPrefs", 0);
        mUsername = prefs.getString("username", null);
        mUsername = "JP";
        if (mUsername == null) {
            Random r = new Random();
            // Assign a random user name if we don't have one saved.
            mUsername = "JP";
            prefs.edit().putString("username", mUsername).commit();
        }
    }

    private void sendMessage() {
        EditText inputText = (EditText) findViewById(R.id.messageInput);
        String input = inputText.getText().toString();
        String inputs[] = input.split(" ");
        int inputNum = inputs.length;

        if (!input.equals("")) {
            String name, item;
            Double qty;
            if (inputNum < 3) {
                name = "JP";
                item = inputs[0];
                qty= Double.parseDouble(inputs[1]);
            }
            else {
                name = inputs[0];
                item = inputs[1];
                qty= Double.parseDouble(inputs[2]);
            }

                Chat chat = new Chat(item, name, qty);
//                mFirebaseRef.push().setValue(chat);
                Firebase itemRef = mFirebaseRef.child(Integer.toString(listNum));
                itemRef.setValue(chat);
                inputText.setText("");
                listNum++;
        }
    }
}
