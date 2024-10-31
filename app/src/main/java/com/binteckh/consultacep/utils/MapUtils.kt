package com.binteckh.consultacep.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun openMapForLocation(context: Context, logradouro: String?) {
    logradouro?.let {
        // Primeiro, tente abrir no Waze
        val wazeUri = Uri.parse("waze://?q=$logradouro")
        val wazeIntent = Intent(Intent.ACTION_VIEW, wazeUri)
        if (wazeIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(wazeIntent)
        } else {
            // Se o Waze não estiver disponível, tenta abrir no Google Maps
            val mapsUri = Uri.parse("geo:0,0?q=$logradouro")
            val mapsIntent = Intent(Intent.ACTION_VIEW, mapsUri)
            mapsIntent.setPackage("com.google.android.apps.maps")
            if (mapsIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapsIntent)
            } else {
                // Se o Google Maps também não estiver disponível, abre em um navegador
                val fallbackUri =
                    Uri.parse("https://www.google.com/maps/search/?api=1&query=$logradouro")
                val fallbackIntent = Intent(Intent.ACTION_VIEW, fallbackUri)
                context.startActivity(fallbackIntent)
            }
        }
    }
}