/*
 * Copyright (c) 2022 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.petit

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raywenderlich.android.petit.network.PhotoListAdapter
import com.raywenderlich.android.petit.network.Photos

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Photos>?) {
  val adapter = recyclerView.adapter as? PhotoListAdapter?
  Log.i("adapter", data.toString())
  adapter?.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
  imgUrl?.let {
    val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
    Glide.with(imgView.context)
        .load(imgUri)
        .apply(RequestOptions()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
        )
        .into(imgView)
  }
}

@BindingAdapter("petitApiStatus")
fun bindStatus(statusImageView: ImageView, status: PetitApiStatus?) {
  when (status) {
    PetitApiStatus.LOADING -> {
      statusImageView.visibility = View.VISIBLE
      statusImageView.setImageResource(R.drawable.loading_animation)
    }
    PetitApiStatus.ERROR -> {
      statusImageView.visibility = View.VISIBLE
      statusImageView.setImageResource(R.drawable.ic_connection_error)
    }
    PetitApiStatus.DONE -> {
      statusImageView.visibility = View.GONE
    }
  }
}