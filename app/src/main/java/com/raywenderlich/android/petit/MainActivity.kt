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

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.petit.databinding.ActivityMainBinding
import com.raywenderlich.android.petit.network.PhotoListAdapter
import java.util.*

/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    binding.lifecycleOwner = this

    viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    binding.viewModel = viewModel

    binding.photosList.adapter = PhotoListAdapter {
      Toast.makeText(this, "${it.user?.name?.capitalize()} Got clicked", Toast.LENGTH_LONG).show()
    }

    val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
      ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or
          ItemTouchHelper.DOWN or ItemTouchHelper.UP, ItemTouchHelper.LEFT or
          ItemTouchHelper.RIGHT
    ) {
      override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
      ): Boolean {
        val from = viewHolder.bindingAdapterPosition
        val to = target.bindingAdapterPosition

        Collections.swap(viewModel.photos.value as MutableList<*>, from, to)

        (binding.photosList.adapter as PhotoListAdapter).notifyItemMoved(from, to)

        return true
      }

      override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
      ) {
        viewModel.photos.value?.removeAt(viewHolder.bindingAdapterPosition)
        (binding.photosList.adapter as PhotoListAdapter).notifyItemRemoved(viewHolder.bindingAdapterPosition)
      }
    })

    helper.attachToRecyclerView(binding.photosList)
    setContentView(binding.root)
  }
}
