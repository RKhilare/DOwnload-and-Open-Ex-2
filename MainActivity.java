package com.example.downloadandviewapllicationex_2;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{

    String url="https://t3.ftcdn.net/jpg/06/18/51/68/360_F_618516899_5MTPaCA96bARDcFysmB2XZYfzuOiJrtx.jpg";
    String fileName="Ram Mandir.jpg";

    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button=findViewById(R.id.downloadBTN);

         button=findViewById(R.id.openBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                download(url,fileName);
                open();
            }
        });

    }

    public void download(String url,String fileName)
    {
        DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDescription("Downloading "+ fileName);
        request.setTitle(fileName);
        request.allowScanningByMediaScanner();
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName);

        DownloadManager manager=(DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        manager.enqueue(request);

    }

    public void open()
    {
        Intent intent = new Intent(Intent.ACTION_VIEW);

        String filePath = "Downloads";
        intent.setDataAndType(Uri.parse(filePath), "*/*");

        if (intent.resolveActivity(getPackageManager()) != null) {
            // Start the activity
            startActivity(intent);
        } else {
            Toast.makeText(this, "File this is not there", Toast.LENGTH_SHORT).show();
        }
    }
}
