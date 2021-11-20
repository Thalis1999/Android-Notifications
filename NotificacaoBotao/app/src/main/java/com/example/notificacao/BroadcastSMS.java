package com.example.notificacao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class BroadcastSMS extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Recuperar os dados (extras) do SMS
        //A classe Bundle é utilizada quando há diversos tipos de extras
        //no objeto
        Bundle extras = intent.getExtras();

        //Testar se o objeto "extras" está vazio ou não
        if (extras != null) {
            //Pegar somente o extra do tipo "pdu". PDU é o protocolo
            //utilizado para monstar a mensagem SMS
            Object[] pdus = (Object[]) extras.get("pdus");

            //Criar um vetor da classe SmsMessage do mesmo tamanho que o "pdus"
            //A classe SmsMessage fornece os métodos para trabalhar com SMS
            SmsMessage[] sms = new SmsMessage[pdus.length];

            String conteudo = "";

            //Laço de repetição para percorrer cada bloco de SMS e
            //recuperar o texto que está dentr ode cada um
            for (int i=0 ; i<pdus.length ; i++) {
                //Converter cada "pdus" em "sms"
                sms[i] = SmsMessage.createFromPdu(
                        (byte[]) pdus[i], extras.getString("format"));
                //Concatenar o texto de cada sms em "conteudo"
                conteudo += sms[i].getMessageBody();

            }
            Toast.makeText(context, conteudo, Toast.LENGTH_SHORT).show();
        }
    }
}