package com.example.grand

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import android.content.ComponentName
import android.app.PendingIntent
import android.content.Intent
import android.widget.Toast


class Widget : AppWidgetProvider() {
    private val ACTION = "open"
    var lock = false
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
        val remoteViews = RemoteViews(context.getPackageName(), R.layout.widget)
        val watchWidget = ComponentName(context, Widget::class.java)
        val intent = Intent(context, javaClass)
        intent.setAction(ACTION)
        val peindingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        remoteViews.setOnClickPendingIntent(R.id.open_button, peindingIntent)
        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (!lock && ACTION.equals(intent.getAction())) {
            lock = true
            Toast.makeText(context, ACTION, 1).show()
            WebhookIntentService.startActionOpen(context);
        }
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    val views = RemoteViews(context.packageName, R.layout.widget)
    appWidgetManager.updateAppWidget(appWidgetId, views)
}
