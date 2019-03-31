package com.zv.geochat.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.zv.geochat.model.ChatMessage;
import com.zv.geochat.model.ChatMessageBody;

public class MapClusterItem implements ClusterItem {
    private final LatLng mPosition;
    private final ChatMessage mMessage;

    public MapClusterItem(ChatMessage message) {
        mPosition = new LatLng(message.getBody().getLat(), message.getBody().getLng());
        mMessage = message;
    }

    public ChatMessage getmMessage() {
        return mMessage;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

}