package com.example.decode;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.annotations.NotNull;

public class ViewModelExt {
    @NotNull
    public static final ViewModelProvider.Factory createFactory(@NotNull final ViewModel $this$createFactory) {
        return new ViewModelProvider.Factory() {
            @NotNull
            public ViewModel create(@NotNull Class modelClass) {
                return $this$createFactory;
            }
        };
    }
}
