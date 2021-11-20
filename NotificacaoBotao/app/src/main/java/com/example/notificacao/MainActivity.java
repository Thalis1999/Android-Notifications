package com.example.notificacao;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //Criar um objeto que representa o serviço de notificação do Android
    NotificationManager servico;
    //Código inteiro para representar a nossa notificação dentro do serviço
    private final int COD_NOTIFICACAO = 8795;
    //Criação de uma identificação para o canal de notificação (Android 8 e acima)
    private final String ID_CANAL = "canalAppNotificacao";

    //Método para verificar qual a versão do Android e, caso seja versão 8 (Oreo)
    //ou acima, irá criar o canal de notificação
    private void criaCanalNotificacao(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //Criação de um nome, descrição e prioridade para o canal
            CharSequence nome = "nomeCanal";
            String descricao = "Descrição do canal App Notificação";
            int prioridade = NotificationManager.IMPORTANCE_DEFAULT;
            //Criação do canal de notificação com os dados acima
            NotificationChannel canal = new NotificationChannel(
                    ID_CANAL, //ID criado no início da classe
                    nome, prioridade);
            canal.setDescription(descricao);

            //Acessar o serviço de notificação do Android e atribuir o nosso canal
            NotificationManager servico = getSystemService(NotificationManager.class);
            //Adicionar o nosso canal ao serviço do Android
            servico.createNotificationChannel(canal);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Verificar se o usuário já permitiu a premissão do SMS
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            //Se o usuário ainda não autorizou, ou seja, diferente de permitido...
            //Exibir janela para que ele autorize ou não a permissão
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS}, 100);
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            //Se o usuário ainda não autorizou, ou seja, diferente de permitido...
            //Exibir janela para que ele autorize ou não a permissão
            ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.RECEIVE_SMS}, 200);
        }

        criaCanalNotificacao();

        Button notificar2 = findViewById(R.id.notificar2);
        notificar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Iniciar o objeto do "servico" e acessar o serviço de
                //notificação do Android
                servico = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                //Criar uma Intent para indicar qual recurso será
                //aberto ao acessar a notificação
                Intent intent = new Intent(MainActivity.this, Tela2.class);
                intent.putExtra("testeExtra", "abrirTela2");

                //Transformar a intent em PendingIntent (para ficar em espera)
                PendingIntent p = PendingIntent.getActivity(
                        MainActivity.this, //Contexto
                        0, //Valor zero por padrão
                        intent, //A intent que será transformada em PendingIntent
                        PendingIntent.FLAG_UPDATE_CURRENT //Modo de execução padrão
                );

                //Configurar a notificação
                NotificationCompat.Builder notificacao =
                        new NotificationCompat.Builder(MainActivity.this, ID_CANAL)
                                .setContentTitle("Título da notificação")
                                .setContentText("Texto que vai na parte interna da notificação")
                                .setAutoCancel(true) //Remove a notificação após clicar/tocar
                                .setContentIntent(p) //Configuração da tela que irá abrir
                                .setSmallIcon(R.drawable.icone_notificacao); //Ícone que irá aparecer na barra de status

                //Configurar o botão "Sim" da notificação
                //Para que o botão execute algo ao clicar, é preciso criar uma Intent
                //e depois configurar uma Action para o Broadcast
                Intent botaoSim = new Intent(MainActivity.this, BroadcastNotificacao.class);
                botaoSim.setAction("CLICOU_BOTAO_SIM");

                PendingIntent btSim = PendingIntent.getBroadcast(
                        MainActivity.this, //Contexto
                        0, //Zero por padrão
                        botaoSim, //Qual intent será configurada como PendingIntent
                        PendingIntent.FLAG_UPDATE_CURRENT); //Tipo de execução

                //Adiciona o botão Sim na notificação
                notificacao.addAction(R.drawable.action_sim, "Sim", btSim);


                //Botão Não
                Intent botaoNao = new Intent(MainActivity.this, BroadcastNotificacao.class);
                botaoNao.setAction("CLICOU_BOTAO_NAO");

                PendingIntent btNao = PendingIntent.getBroadcast(
                        MainActivity.this, //Contexto
                        0, //Zero por padrão
                        botaoNao, //Qual intent será configurada como PendingIntent
                        PendingIntent.FLAG_UPDATE_CURRENT); //Tipo de execução

                //Adiciona o botão Sim na notificação
                notificacao.addAction(R.drawable.action_nao, "Não", btNao);

                //Exibir a notificação
                servico.notify(COD_NOTIFICACAO, notificacao.build());
            }
        });

        Button notificar = findViewById(R.id.notificar);
        notificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Iniciar o objeto do "servico" e acessar o serviço de
                //notificação do Android
                servico = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                //Criar uma Intent para indicar qual recurso será
                //aberto ao acessar a notificação
                Intent intent = new Intent(MainActivity.this, Tela2.class);
                intent.putExtra("testeExtra", "abrirTela2");

                //Transformar a intent em PendingIntent (para ficar em espera)
                PendingIntent p = PendingIntent.getActivity(
                        MainActivity.this, //Contexto
                        0, //Valor zero por padrão
                        intent, //A intent que será transformada em PendingIntent
                        PendingIntent.FLAG_UPDATE_CURRENT //Modo de execução padrão
                );

                //Configurar a notificação
                NotificationCompat.Builder notificacao =
                    new NotificationCompat.Builder(MainActivity.this, ID_CANAL)
                        .setContentTitle("Título da notificação")
                        .setContentText("Texto que vai na parte interna da notificação")
                        .setAutoCancel(true) //Remove a notificação após clicar/tocar
                        .setContentIntent(p) //Configuração da tela que irá abrir
                        .setSmallIcon(R.drawable.icone_notificacao); //Ícone que irá aparecer na barra de status

                //Exibir a notificação
                servico.notify(COD_NOTIFICACAO, notificacao.build());
            }
        });
    }
}