package okunev.com.cleancode.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import okunev.com.cleancode.R;

/**
 * Project CleanCode. Created by gwa on 9/23/16.
 */

public class ModelView extends RelativeLayout {
    Context context;
    String name = "Name 0", description = "Description 0", link = "http://goodworkapps.com";
    TextView nameView, descriptionView, linkView;

    public ModelView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ModelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setupAttrs(attrs);
        init();
    }

    public ModelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setupAttrs(attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ModelView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        setupAttrs(attrs);
        init();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        nameView.setText(name);
    }

    public String getDescription() {
        return description;

    }

    public void setDescription(String description) {
        this.description = description;
        descriptionView.setText(description);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        linkView.setText(link);
    }

    void setupAttrs(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ModelView, 0, 0);
        name = a.getString(R.styleable.ModelView_nameText);
        description = a.getString(R.styleable.ModelView_descriptionText);
        link = a.getString(R.styleable.ModelView_linkText);
        a.recycle();
    }

    void init() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.model_view, this, true);
        nameView = (TextView) findViewById(R.id.modelName);
        descriptionView = (TextView) findViewById(R.id.modelDescription);
        linkView = (TextView) findViewById(R.id.modelLink);


        linkView.setTextColor(Color.BLUE);
        linkView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(link)));
            }
        });
    }
}
