package com.turtlediary.www.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.turtlediary.www.R;
import com.turtlediary.www.beans.GameListModel;
import com.turtlediary.www.interfaces.GameItemClickListner;

import java.util.List;

/**
 * Created by arvind on 31/10/17.
 */

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameListViewHolder> {


    private List<GameListModel> mGameList;
    private Context mContext ;
    private GameItemClickListner gameItemClickListner;

   public  GameListAdapter(Context context, List<GameListModel> gameList, GameItemClickListner gameItemClickListner){
        this.mContext =  context;
        this.mGameList = gameList;
       this.gameItemClickListner=gameItemClickListner;
    }

    @Override
    public GameListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.game_list_item, viewGroup, false);
        GameListViewHolder viewHolder = new GameListViewHolder(view);
        viewHolder.setIsRecyclable(false);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(GameListViewHolder holder,final  int position) {
      /*     if(mGameList.get(position).getIsBlock()){
            holder.mFreeBannerImage.setVisibility(View.GONE);

        }else
        {
            holder.mFreeBannerImage.setVisibility(View.VISIBLE);

        }*/
        holder.mFreeBannerImage.setVisibility(View.GONE);
        holder.llGameContainer.setVisibility(View.VISIBLE);
        holder.mTxtGameTitle.setText(mGameList.get(position).getGameName());
        loadImages(holder.mGameIcon, mGameList.get(position).getImageName());
        holder.llGameContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameItemClickListner.openGame(mGameList.get(position));

            }
        });
    }

    private void loadImages(ImageView img, String imgUrl) {

        Glide.with(mContext)
                .load(imgUrl).into(img);
    }

    @Override
    public int getItemCount() {
        return mGameList.size();
    }

    public class GameListViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mFreeBannerImage;
        private ImageView mGameIcon;
        private TextView mTxtGameTitle;
        private LinearLayout llGameContainer;

        public GameListViewHolder(View itemView) {
            super(itemView);

            mFreeBannerImage = (ImageView)itemView.findViewById(R.id.img_game_free_banner);
            mGameIcon = (ImageView)itemView.findViewById(R.id.img_game);
            mTxtGameTitle  =(TextView)itemView.findViewById(R.id.txt_title_game);
            llGameContainer  =(LinearLayout) itemView.findViewById(R.id.game_container);



        }
    }

}
