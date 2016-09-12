package cn.edu.cdut.lm.speaker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by LimiaoMaster on 2016/8/24 18:37
 */
class SingleSongRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private  Context context;
    private  List<Mp3Info> mp3InfoList;
    private int listPosition;
    private int lastPosition = -1;

    SingleSongRVAdapter(Context context, List<Mp3Info> list) {
        this.context = context;
        this.mp3InfoList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewOfGeneralLines = LayoutInflater.from(context).inflate(R.layout.item_localmusic_singlesong,parent,false);
        return new GeneralLinesViewHolder(viewOfGeneralLines);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        Mp3Info mp3Info;
            mp3Info = mp3InfoList.get(position);
            ((GeneralLinesViewHolder) holder).title.setText(mp3Info.getTitle());
            ((GeneralLinesViewHolder) holder).artist.setText(mp3Info.getArtist());
            ((GeneralLinesViewHolder) holder).album.setText(mp3Info.getAlbum());

            /*((GeneralLinesViewHolder) holder).view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listPosition = position;
                    if (listPosition != lastPosition) {
                            setThisItemSelected(position);
                            if (mp3InfoList.get(position).isSelected()){
                                ((GeneralLinesViewHolder) holder).speaker.setVisibility(View.VISIBLE);
                            }
                        }
                    lastPosition = listPosition;
                    }
            });*/
    }

    private void setThisItemSelected(int position) {
        for (int i = 0 ; i<mp3InfoList.size(); i++){
            mp3InfoList.get(i).setSelected(position == i);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
       return mp3InfoList.size();
    }

    private class GeneralLinesViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView artist;
        TextView album;
        ImageView more;
        ImageView speaker;
        View view;
        GeneralLinesViewHolder(View viewOfGeneralLines) {
            super(viewOfGeneralLines);
            view = viewOfGeneralLines;
            title = (TextView) viewOfGeneralLines.findViewById(R.id.title_localmusic);
            artist = (TextView) viewOfGeneralLines.findViewById(R.id.artist_localmusic);
            album = (TextView) viewOfGeneralLines.findViewById(R.id.album_localmusic);
            more = (ImageView) viewOfGeneralLines.findViewById(R.id.iv_more_localmusic);
            speaker = (ImageView) viewOfGeneralLines.findViewById(R.id.speaker);
            viewOfGeneralLines.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listPosition = getAdapterPosition();
                    Log.v("onClick()","您点击的位置是："+listPosition);

                    if (listPosition != lastPosition) {
                        setThisItemSelected(listPosition);
                        if (mp3InfoList.get(listPosition).isSelected()){
                            speaker.setVisibility(View.VISIBLE);
                        }
                    }
                    lastPosition = listPosition;
                }
            });
        }
    }
}
