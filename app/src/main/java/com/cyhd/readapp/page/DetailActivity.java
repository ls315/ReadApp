package com.cyhd.readapp.page;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cyhd.readapp.R;
import com.cyhd.readapp.bean.ReadDetailData;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.net.URL;

import java.util.logging.LogRecord;

public class DetailActivity extends AppCompatActivity {


    private TextView title;
    private TextView source;
    private TextView body;
    private ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = (TextView) findViewById(R.id.detail_text_title);
        source = (TextView) findViewById(R.id.detail_text_source);
        body = (TextView) findViewById(R.id.detail_text_body);
        image = (ImageView) findViewById(R.id.image);

        initData();
    }

    Html.ImageGetter imgGetter = new Html.ImageGetter() {

        Drawable drawable =null;

        @Override
        public Drawable getDrawable(String source) {

            ImageLoader.getInstance().loadImage(source, new SimpleImageLoadingListener() {

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {


                    drawable = new BitmapDrawable(loadedImage);

                    boolean b = drawable == null;

//                    Toast.makeText(DetailActivity.this, b + "", Toast.LENGTH_SHORT).show();

                    Log.d("DetailActivity", "getDrawable()");

                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

//                    image.setImageDrawable(drawable);
//
                    String img = "<img src=\"http://easyread.ph.126.net/KVQz0bWRNGJmasYBVibHLA==/7917019182154056069.jpg\" width=\"304\" height=\"228\"/>";


                    body.invalidate();
                    body.setText(body.getText());




                }
            });

            return drawable;
        }

    };


    private void initData() {

        ReadDetailData data = (ReadDetailData) getIntent().getSerializableExtra("ReadDetailData");

        title.setText(data.getTitle());
        source.setText(data.getSource());

        String src = data.getImg().get(0).getSrc();

        String img = "<img src=\"http://easyread.ph.126.net/KVQz0bWRNGJmasYBVibHLA==/7917019182154056069.jpg\" />";

        String html = data.getBody();
//        body.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动

//        body.setText(Html.fromHtml(html));

        html.replace("<!--IMG#0-->", img);

//        body.setText(Html.fromHtml(img, imgGetter, null));

        Spanned sp = Html.fromHtml(img, imgGetter, null);
        body.setMovementMethod(LinkMovementMethod.getInstance());

        body.setText(sp);


    }

}
