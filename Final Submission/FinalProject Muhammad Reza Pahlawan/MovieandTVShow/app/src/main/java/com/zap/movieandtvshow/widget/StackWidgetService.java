package com.zap.movieandtvshow.widget;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.squareup.picasso.Picasso;
import com.zap.movieandtvshow.R;
import com.zap.movieandtvshow.localdb.AppDatabase;
import com.zap.movieandtvshow.localdb.DBRepository;
import com.zap.movieandtvshow.model.MovieFavourite;

import java.util.List;

public class StackWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new WidgetRemoteViewsFactory(this.getApplicationContext());
    }
}