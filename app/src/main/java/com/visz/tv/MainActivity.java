package com.visz.tv;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.VideoSize;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.visz.tv.databinding.ActivityMainBinding;
import com.visz.tv.utils.LogUtil;
import com.visz.tv.utils.ChannelUtil;
import com.visz.tv.utils.SharePref;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    private List<Channel> channelList = new ArrayList<>();
    protected List<MediaItem> mediaItemList = new ArrayList<>();
    private final int MSG_SHOW_CHANNEL_LIST = 10001;
    private final int MSG_HIDE_CHANNEL_LIST = 10002;
    private long HIDE_CHANNEL_TIMEOUT = 5000;
    private @Nullable ExoPlayer player;
    private @Nullable ChannelAdapter channelAdapter;
    private @Nullable LinearLayoutManager linearLayoutManager;
    private int lastPlayIndex = 0;
    private long lastBackTime = 0;
    private String LAST_PLAY_INDEX = "lastPlayIndex";
    private float aspectRatio = 16f / 9f;
    private GestureDetector gestureDetector;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SHOW_CHANNEL_LIST:
                    showChannelList();
                    break;
                case MSG_HIDE_CHANNEL_LIST:
                    hideChannelList();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {
        lastPlayIndex = (int) SharePref.get(getApplicationContext(), LAST_PLAY_INDEX, 0);
        channelList = ChannelUtil.getChannelList(this);
        if (channelList.size() > 0) {
            mediaItemList.clear();
            for (Channel channel : channelList) {
                MediaItem item = MediaItem.fromUri(channel.url);
                mediaItemList.add(item);
            }
        }
        gestureDetector = new GestureDetector(this, new GestureListener());
        initPlayer();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvChannelList.setLayoutManager(linearLayoutManager);
        channelAdapter = new ChannelAdapter(binding.rvChannelList);
        binding.rvChannelList.setAdapter(channelAdapter);
        binding.rvChannelList.addItemDecoration(new ChannelItemDecoration(10));

        binding.rvChannelList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                resetHideTimeout();
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                resetHideTimeout();
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        binding.playerView.getPlayer().addListener(new Player.Listener() {
            @Override
            public void onVideoSizeChanged(VideoSize videoSize) {
                float ratio = (float) binding.playerView.getMeasuredWidth() / binding.playerView.getMeasuredHeight();
                ViewGroup.LayoutParams layoutParams = binding.playerView.getLayoutParams();
                if (ratio < aspectRatio) {
                    layoutParams.height = (int) (binding.playerView.getMeasuredWidth() / aspectRatio);
                    binding.playerView.setLayoutParams(layoutParams);
                } else if (ratio > aspectRatio) {
                    layoutParams.width = (int) (binding.playerView.getMeasuredHeight() * aspectRatio);
                    binding.playerView.setLayoutParams(layoutParams);
                }
            }

            @Override
            public void onPlayerError(PlaybackException error) {
                LogUtil.e("PlaybackException " + error);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT > 23) {
            initPlayer();
            showChannelList();
            binding.playerView.onResume();
            play(lastPlayIndex);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT <= 23) {
            initPlayer();
            showChannelList();
            binding.playerView.onResume();
            play(lastPlayIndex);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Build.VERSION.SDK_INT <= 23) {
            binding.playerView.onPause();
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Build.VERSION.SDK_INT > 23) {
            binding.playerView.onPause();
            releasePlayer();
        }
    }

    @Override
    public void onBackPressed() {
        if (binding.rvChannelList.getVisibility() == View.VISIBLE) {
            hideChannelList();
        } else {
            if (System.currentTimeMillis() - lastBackTime > 2000) {
                Toast.makeText(this, R.string.twice_to_exit_app, Toast.LENGTH_LONG).show();
                lastBackTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.d("onKeyDown:" + keyCode);
        if (binding.rlChannelListContainer.getVisibility() != View.VISIBLE) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_MENU || keyCode == KeyEvent.KEYCODE_ENTER) {
                showChannelList();
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                lastPlayIndex++;
                play(lastPlayIndex);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP || keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                lastPlayIndex--;
                play(lastPlayIndex);
            }
        } else {
            if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER || keyCode == KeyEvent.KEYCODE_MENU || keyCode == KeyEvent.KEYCODE_ENTER) {
                int focusIndex = binding.rvChannelList.getChildAdapterPosition(binding.rvChannelList.getFocusedChild());
                play(focusIndex);
                resetHideTimeout();
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void play(int index) {
        index += mediaItemList.size();
        index %= mediaItemList.size();
        if (player != null && index >= 0 && index < mediaItemList.size()) {
            player.setMediaItem(mediaItemList.get(index));
            player.prepare();
            lastPlayIndex = index;
        }
    }

    private void initPlayer() {
        if (player == null) {
            player = new ExoPlayer.Builder(getApplicationContext()).build();
            binding.playerView.setPlayer(player);
            binding.playerView.setUseController(false);
            player.setPlayWhenReady(true);
        }
    }

    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
        binding.playerView.setPlayer(null);
    }

    class ChannelItemDecoration extends RecyclerView.ItemDecoration {
        private final int gap;

        public ChannelItemDecoration(int gap) {
            this.gap = gap;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.bottom = gap;
            outRect.right = gap;
        }
    }

    class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelHolder> {
        private RecyclerView recyclerView;
        private View.OnClickListener mOnClickListener = view -> {
            resetHideTimeout();
            if (view instanceof ChannelItem) {
                ChannelItem item = (ChannelItem) view;
                item.binding.llChannelItemContainer.requestFocus();
                int pos = 0;
                for (Channel channel : channelList) {
                    if (channel.num.equals(item.binding.getChannel().num)) {
                        play(pos);
                        break;
                    }
                    pos++;
                }
            }
        };

        public ChannelAdapter(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Override
        public ChannelAdapter.ChannelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ChannelItem channelItem = new ChannelItem(parent.getContext());
            return new ChannelAdapter.ChannelHolder(channelItem);
        }

        @Override
        public void onBindViewHolder(@NonNull ChannelAdapter.ChannelHolder holder, int position) {
            ChannelItem channelItem = (ChannelItem) holder.itemView;
            if (position < channelList.size()) {
                channelItem.binding.setChannel(channelList.get(position));
            }
            if (position < mediaItemList.size()) {
                channelItem.setOnClickListener(mOnClickListener);
            }
            if (lastPlayIndex == position) {
                channelItem.binding.llChannelItemContainer.requestFocus();
            }
        }

        @Override
        public int getItemCount() {
            return mediaItemList.size();
        }

        class ChannelHolder extends RecyclerView.ViewHolder {
            public ChannelHolder(@NonNull final View itemView) {
                super(itemView);
                itemView.setOnFocusChangeListener((view, focus) -> {
                    if (view instanceof ChannelItem) {
                        ChannelItem item = (ChannelItem) view;
                        if (focus) {
                            item.binding.llChannelItemContainer.requestFocus();
                        }
                    }
                });
            }
        }
    }

    private void resetHideTimeout() {
        handler.removeMessages(MSG_HIDE_CHANNEL_LIST);
        handler.sendEmptyMessageDelayed(MSG_HIDE_CHANNEL_LIST, HIDE_CHANNEL_TIMEOUT);
    }

    private void showChannelList() {
        channelAdapter.notifyDataSetChanged();
        LogUtil.i("showChannelList :" + lastPlayIndex);
        if (linearLayoutManager != null) {
            linearLayoutManager.scrollToPosition(lastPlayIndex);
        }
        binding.rlChannelListContainer.setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.channel_up_in);
        binding.rvChannelList.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                binding.rvChannelList.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                resetHideTimeout();
            }
        });
    }

    public void hideChannelList() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.channel_down_out);
        binding.rvChannelList.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                binding.rlChannelListContainer.setVisibility(View.GONE);
            }
        });
    }

    class GestureListener implements GestureDetector.OnGestureListener {

        @Override
        public boolean onDown(@NonNull MotionEvent motionEvent) {
            LogUtil.d("onDown");
            return false;
        }

        @Override
        public void onShowPress(@NonNull MotionEvent motionEvent) {
            LogUtil.d("onShowPress");
        }

        @Override
        public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
            LogUtil.d("onSingleTapUp");
            if (binding.rlChannelListContainer.getVisibility() != View.VISIBLE) {
                showChannelList();
                resetHideTimeout();
            } else {
                hideChannelList();
            }
            return false;
        }

        @Override
        public boolean onScroll(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
            LogUtil.d("onScroll");
            return false;
        }

        @Override
        public void onLongPress(@NonNull MotionEvent motionEvent) {
            LogUtil.d("onLongPress");
        }

        @Override
        public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            LogUtil.d("onFling：" + velocityX + "," + velocityY);
            if (Math.abs(velocityX) < Math.abs(velocityY)) {
                return true;
            }
            if (velocityX > 0) {
                if (binding.rlChannelListContainer.getVisibility() != View.VISIBLE) {
                    lastPlayIndex--;
                    play(lastPlayIndex);
                }
            }
            if (velocityX < 0) {
                if (binding.rlChannelListContainer.getVisibility() != View.VISIBLE) {
                    lastPlayIndex++;
                    play(lastPlayIndex);
                }
            }
            return true;
        }
    }
}
