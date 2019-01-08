package dev452.com.whatsappedittext_lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Debojyoti Singha on 09,January,2019.
 */
@SuppressLint("AppCompatCustomView")
public class WhatsAppTextView extends TextView {

    private ArrayList<TextWatcher> mListeners;

    public WhatsAppTextView(Context context) {
        super(context);
        init();
    }

    public WhatsAppTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.WhatsAppTextView);
        final String fontPath = a.getString(R.styleable.WhatsAppTextView_whatsTextFontPath);
        a.recycle();
        setTypeFace(fontPath);
        init();
    }

    public WhatsAppTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WhatsAppTextView);
        final String fontPath = a.getString(R.styleable.WhatsAppTextView_whatsTextFontPath);
        a.recycle();
        setTypeFace(fontPath);
        init();
    }

    @SuppressLint("NewApi")
    public WhatsAppTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    public void setTypeFace(String fontPath) {
        if(fontPath != null) {
            Typeface myTypeFace = Typeface.createFromAsset(this.getContext().getAssets(), fontPath);
            this.setTypeface(myTypeFace);
        }
    }

    private void init() {
        addTextChangedListener(mEditTextWatcher);
    }


    private TextWatcher mEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            sendBeforeTextChanged(s, start, count, after);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            sendOnTextChanged(s, start, before, count);
        }

        @Override
        public void afterTextChanged(Editable s) {

            CharSequence formatted = WhatsappViewCompat.extractFlagsForTextView(s);

            removeTextChangedListener(mEditTextWatcher);
            setText(formatted, BufferType.EDITABLE);
            Editable formattedEditableText = (Editable) getText();
            sendAfterTextChanged(formattedEditableText);
            addTextChangedListener(mEditTextWatcher);


        }
    };


    private void sendBeforeTextChanged(CharSequence s, int start, int count, int after) {
        if (mListeners != null) {
            for (int i = 0; i < mListeners.size(); i++) {
                mListeners.get(i).beforeTextChanged(s, start, count, after);
            }
        }
    }


    private void sendOnTextChanged(CharSequence s, int start, int before, int count) {
        if (mListeners != null) {
            for (int i = 0; i < mListeners.size(); i++) {
                mListeners.get(i).onTextChanged(s, start, before, count);
            }
        }
    }


    private void sendAfterTextChanged(Editable s) {
        if (mListeners != null) {
            for (int i = 0; i < mListeners.size(); i++) {
                mListeners.get(i).afterTextChanged(s);
            }
        }
    }


    public void addTextChangedListener(TextWatcher watcher) {

        if (watcher != mEditTextWatcher) {
            if (mListeners == null) {
                mListeners = new ArrayList<>();
            }

            mListeners.add(watcher);
        } else {
            super.addTextChangedListener(watcher);
        }
    }


    public void removeTextChangedListener(TextWatcher watcher) {
        if (watcher != mEditTextWatcher) {
            if (mListeners != null) {
                int i = mListeners.indexOf(watcher);

                if (i >= 0) {
                    mListeners.remove(i);
                }
            }
        } else {
            super.removeTextChangedListener(watcher);
        }
    }
}
