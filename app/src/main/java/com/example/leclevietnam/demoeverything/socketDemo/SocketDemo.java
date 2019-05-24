package com.example.leclevietnam.demoeverything.socketDemo;

import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.leclevietnam.demoeverything.R;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketDemo extends AppCompatActivity {

    private EditText etMessage;
    private Button btnSend;
    private Socket socket;
    private PrintWriter printWriter;
    private OutputStream out;

    private Handler handler;
    private Thread thread;

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_demo);

        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            if (socket.isConnected()) {
                                byte[] encoded = etMessage.getText().toString().getBytes(StandardCharsets.UTF_8);
//                                dataOutputStream.writeInt(encoded.length);
                                dataOutputStream.write(encoded);
                                dataOutputStream.flush();
                            } else {
                                setupSocket();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                if (etMessage.getText().toString().equals("close")) {
                    try {
                        dataInputStream.close();
                        dataOutputStream.close();
//                        out.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        setupSocket();
    }

    private void setupSocket() {
        handler = new Handler();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("192.168.0.26", 12345);

                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataInputStream = new DataInputStream(socket.getInputStream());

//                    out = socket.getOutputStream();

//                    printWriter = new PrintWriter(out);

                } catch (IOException e) {
                    Log.d("wtf", "run: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
