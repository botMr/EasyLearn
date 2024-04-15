// Generated by view binder compiler. Do not edit!
package com.example.easylearn.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.easylearn.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAiScreenBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonAi;

  @NonNull
  public final Button buttonHome;

  @NonNull
  public final Button buttonLib;

  @NonNull
  public final ConstraintLayout constraintLayout;

  @NonNull
  public final ConstraintLayout constraintLayout2;

  @NonNull
  public final ConstraintLayout constraintLayout3;

  @NonNull
  public final ImageView imageViewProfile;

  @NonNull
  public final EditText inputGPT;

  @NonNull
  public final RecyclerView recyclerView;

  @NonNull
  public final ImageButton sendBtn;

  @NonNull
  public final TextView textViewMark;

  private ActivityAiScreenBinding(@NonNull ConstraintLayout rootView, @NonNull Button buttonAi,
      @NonNull Button buttonHome, @NonNull Button buttonLib,
      @NonNull ConstraintLayout constraintLayout, @NonNull ConstraintLayout constraintLayout2,
      @NonNull ConstraintLayout constraintLayout3, @NonNull ImageView imageViewProfile,
      @NonNull EditText inputGPT, @NonNull RecyclerView recyclerView, @NonNull ImageButton sendBtn,
      @NonNull TextView textViewMark) {
    this.rootView = rootView;
    this.buttonAi = buttonAi;
    this.buttonHome = buttonHome;
    this.buttonLib = buttonLib;
    this.constraintLayout = constraintLayout;
    this.constraintLayout2 = constraintLayout2;
    this.constraintLayout3 = constraintLayout3;
    this.imageViewProfile = imageViewProfile;
    this.inputGPT = inputGPT;
    this.recyclerView = recyclerView;
    this.sendBtn = sendBtn;
    this.textViewMark = textViewMark;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAiScreenBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAiScreenBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_ai_screen, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAiScreenBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonAi;
      Button buttonAi = ViewBindings.findChildViewById(rootView, id);
      if (buttonAi == null) {
        break missingId;
      }

      id = R.id.buttonHome;
      Button buttonHome = ViewBindings.findChildViewById(rootView, id);
      if (buttonHome == null) {
        break missingId;
      }

      id = R.id.buttonLib;
      Button buttonLib = ViewBindings.findChildViewById(rootView, id);
      if (buttonLib == null) {
        break missingId;
      }

      id = R.id.constraintLayout;
      ConstraintLayout constraintLayout = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout == null) {
        break missingId;
      }

      id = R.id.constraintLayout2;
      ConstraintLayout constraintLayout2 = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout2 == null) {
        break missingId;
      }

      id = R.id.constraintLayout3;
      ConstraintLayout constraintLayout3 = ViewBindings.findChildViewById(rootView, id);
      if (constraintLayout3 == null) {
        break missingId;
      }

      id = R.id.imageViewProfile;
      ImageView imageViewProfile = ViewBindings.findChildViewById(rootView, id);
      if (imageViewProfile == null) {
        break missingId;
      }

      id = R.id.inputGPT;
      EditText inputGPT = ViewBindings.findChildViewById(rootView, id);
      if (inputGPT == null) {
        break missingId;
      }

      id = R.id.recyclerView;
      RecyclerView recyclerView = ViewBindings.findChildViewById(rootView, id);
      if (recyclerView == null) {
        break missingId;
      }

      id = R.id.sendBtn;
      ImageButton sendBtn = ViewBindings.findChildViewById(rootView, id);
      if (sendBtn == null) {
        break missingId;
      }

      id = R.id.textViewMark;
      TextView textViewMark = ViewBindings.findChildViewById(rootView, id);
      if (textViewMark == null) {
        break missingId;
      }

      return new ActivityAiScreenBinding((ConstraintLayout) rootView, buttonAi, buttonHome,
          buttonLib, constraintLayout, constraintLayout2, constraintLayout3, imageViewProfile,
          inputGPT, recyclerView, sendBtn, textViewMark);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
