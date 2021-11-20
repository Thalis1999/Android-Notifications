package com.example.notificacao;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BroadcastNotificacao extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //Verificar qual a Action acionada
        switch (intent.getAction()){
            case "CLICOU_BOTAO_SIM":
                Toast.makeText(context, "Botão Sim",
                                            Toast.LENGTH_SHORT).show();
            break;

            case "CLICOU_BOTAO_NAO":
                Toast.makeText(context, "Botão Não",
                                        Toast.LENGTH_SHORT).show();
            break;
        }
    }
}