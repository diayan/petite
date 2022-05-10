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

package com.raywenderlich.android.petit.Network

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.petit.databinding.ItemLayoutPhotoBinding

class PhotoListAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Photos, PhotoListAdapter.PhotoListViewHolder>(DiffCallback) {

  class PhotoListViewHolder(private var binding: ItemLayoutPhotoBinding) : RecyclerView.ViewHolder
  (binding.root) {
    fun bind(photo: Photos) {
      binding.photo = photo
      //force data binding to execute immediately
      binding.executePendingBindings()
    }
  }

  companion object DiffCallback : DiffUtil.ItemCallback<Photos>() {

    override fun areItemsTheSame(oldItem: Photos, newItem: Photos): Boolean {
      return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Photos, newItem: Photos): Boolean {
      return oldItem.id == newItem.id
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
    return PhotoListViewHolder(ItemLayoutPhotoBinding.inflate(LayoutInflater.from(parent.context)))
  }

  override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
    val photo = getItem(position)
    holder.bind(photo)
  }

  class OnClickListener(val clickListener: (photo: Photos) -> Unit) {

    fun onClick(photo: Photos) = clickListener(photo)
  }
}