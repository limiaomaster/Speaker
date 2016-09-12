package cn.edu.cdut.lm.speaker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LimiaoMaster on 2016/8/24 18:37
 */
class SingleSongRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Mp3Info> mp3InfoList = new ArrayList<>();
    private int listPosition;
    private int lastPosition = -1;

    public SingleSongRVAdapter(Context context, List<Mp3Info> list) {
        this.context = context;
        this.mp3InfoList.addAll(list);
    }

    public void addData(List<Mp3Info> list) {
        if (list == null)
            return;
        mp3InfoList.addAll(list);
        notifyDataSetChanged();
    }

    private void selectSongs(int position){
        if(position>=mp3InfoList.size())
            return;
        for(int i=0;i<mp3InfoList.size();i++){
            if(i==position){
                mp3InfoList.get(i).setSelected(true);
            }else {
                mp3InfoList.get(i).setSelected(false);
            }
        }
        notifyDataSetChanged();
        Log.e("selectSongs","选中了您点击的歌曲！  "+position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewOfGeneralLines = LayoutInflater.from(context).inflate(R.layout.item_localmusic_singlesong, parent, false);
        return new GeneralLinesViewHolder(viewOfGeneralLines);
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final Mp3Info mp3Info;
        mp3Info = mp3InfoList.get(position);
        ((GeneralLinesViewHolder) holder).title.setText(mp3Info.getTitle());
        ((GeneralLinesViewHolder) holder).artist.setText(mp3Info.getArtist());
        ((GeneralLinesViewHolder) holder).album.setText(mp3Info.getAlbum());
        ((GeneralLinesViewHolder) holder).speaker.setVisibility(mp3Info.isSelected()?View.VISIBLE:View.GONE);
        Log.e("onBindViewHolder","speaker，的显示已更新！ "+position+(mp3Info.isSelected()?View.VISIBLE:View.GONE));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp3Info.isSelected()){
                    Log.e("onBindViewHolder","该行已经被选中，直接返回！");
                    return;
                }
                selectSongs(position);
                Log.e("onBindViewHolder","该行尚未被选中，已经把它设为选中状态！ "+position);

            }
        });
    }

    private void setThisItemSelected(int position) {
        for (int i = 0; i < mp3InfoList.size(); i++) {
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
            /*viewOfGeneralLines.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listPosition = getAdapterPosition();
                    Log.v("onClick()", "您点击的位置是：" + listPosition);

                    if (listPosition != lastPosition) {
                        setThisItemSelected(listPosition);
                        if (mp3InfoList.get(listPosition).isSelected()) {
                            speaker.setVisibility(View.VISIBLE);
                        }
                    }
                    lastPosition = listPosition;
                }
            });*/
        }
    }
}
