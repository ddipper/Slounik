package com.example.slounik;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] text = new String[10];



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Document doc = Jsoup.connect("https://www.skarnik.by/litara/%D0%94" + "").userAgent("Chrome/4.0.249.0 Safari/532.5").cookie("beget", "begetok").referrer("http://www.google.com").get();

                    Elements word = doc.select("ul.unstyled > li");

                    int i = 0;
                    for(Element el : word){
                        i++;
                    }

                    int index = (int) (Math.random() * i);

                    text[0] = word.get(index).text();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(text[0]);
            }
        });
    }
}