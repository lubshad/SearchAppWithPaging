package com.example.searchPaging.utils

import androidx.appcompat.widget.SearchView

inline fun SearchView.onSubmitText(crossinline listener: (query:String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            if (query!= null) {
                listener(query)
            }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    })
}