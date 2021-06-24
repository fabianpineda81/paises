// Generated by view binder compiler. Do not edit!
package com.example.paises.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewpager2.widget.ViewPager2;
import com.example.paises.R;
import com.google.android.material.textview.MaterialTextView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityVistaPaisesBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final LinearLayout contenedorPuntos;

  @NonNull
  public final MaterialTextView nombreContinente;

  @NonNull
  public final ViewPager2 viewPagerContinentes;

  private ActivityVistaPaisesBinding(@NonNull CoordinatorLayout rootView,
      @NonNull LinearLayout contenedorPuntos, @NonNull MaterialTextView nombreContinente,
      @NonNull ViewPager2 viewPagerContinentes) {
    this.rootView = rootView;
    this.contenedorPuntos = contenedorPuntos;
    this.nombreContinente = nombreContinente;
    this.viewPagerContinentes = viewPagerContinentes;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityVistaPaisesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityVistaPaisesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_vista_paises, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityVistaPaisesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.contenedor_puntos;
      LinearLayout contenedorPuntos = rootView.findViewById(id);
      if (contenedorPuntos == null) {
        break missingId;
      }

      id = R.id.nombre_continente;
      MaterialTextView nombreContinente = rootView.findViewById(id);
      if (nombreContinente == null) {
        break missingId;
      }

      id = R.id.view_pager_continentes;
      ViewPager2 viewPagerContinentes = rootView.findViewById(id);
      if (viewPagerContinentes == null) {
        break missingId;
      }

      return new ActivityVistaPaisesBinding((CoordinatorLayout) rootView, contenedorPuntos,
          nombreContinente, viewPagerContinentes);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
