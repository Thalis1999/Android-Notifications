package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  MainActivity extends AppCompatActivity {

    //criar um id para o canal de notificação (utilizado se for Android 8 ou acima)
    private final String ID_CANAL = "canalNotificacaoApp";

    //Objeto que irá receber o serviço de notificação do android
    private NotificationManager servico;

    //Criação de código para indentificar/representar a nossa notificação
    private final int CODIGO_NOTIFICACAO = 1458;//QUALQUER NUMERO INTERIO

    //Método para verificar a versão do Android no dispositivo e criar o
    //canal de norificação caso seja Android 8 ou acima

    private void criarCanalNotificacao(){
        //verificar a versão do Android . se a versão >= android O (Oreo)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            //Para criar o canal é nescessario um nome, descricao, id e prioridade
            CharSequence nome = "Canal App Notificação";

            String desccricao = "Canal utilizado pelo App notificação";
            int prioridade = NotificationManager.IMPORTANCE_DEFAULT;

            //criar  o canal com as notificações montadas acima
            NotificationChannel canal = new NotificationChannel(ID_CANAL,nome,prioridade);
            canal.setDescription(desccricao);

            //Passar o canal criado paa o serviço do Android
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(canal);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        criarCanalNotificacao();

        Button notificar = findViewById(R.id.notificar);
        notificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //iniciar o objeto "servico"
                servico = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Intent tela2 = new Intent(MainActivity.this, Tela2.class);
                tela2.putExtra("abrirFragment", "Usuario");

                //Transformar a intent em PendingIntent
                PendingIntent p = PendingIntent.getActivity(MainActivity.this,
                                                                0,
                                                                tela2,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                //configurar notificação

                NotificationCompat.Builder notificacao = new NotificationCompat.Builder(MainActivity.this, ID_CANAL)
                        .setContentTitle("Titulo da notificação")
                .setContentText("Conteúdo de texto da notificação")
                        .setContentIntent(p)
                        .setAutoCancel(true)
                        .setSmallIcon(R.drawable.icone_notificacao);

                //Exibir a notificação
                servico.notify(CODIGO_NOTIFICACAO, notificacao.build());


            }
        });

    }


}