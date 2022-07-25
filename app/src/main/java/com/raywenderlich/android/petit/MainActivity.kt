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
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.petit.network.PhotoListAdapter


/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {

  private lateinit var adapter: PhotoListAdapter
  private lateinit var recyclerView: RecyclerView
  private lateinit var statusImageView: ImageView
  private lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.AppTheme)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    recyclerView = findViewById(R.id.photos_list)
    statusImageView = findViewById(R.id.status_image)
    viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    viewModel.photos.observe(this, Observer {
      adapter = PhotoListAdapter(it) {
      }
      Log.i("adapter", it.toString())

      recyclerView.adapter = adapter
      recyclerView.layoutManager = LinearLayoutManager(this)
    })

    //TODO 8: Implement clicking/selecting an item

    //TODO 9: Create ItemTouchHelper object and override its methods onMove and onSwiped

    /**
     * Implement Drag and drop in the onMove method
     * **/
    //TODO 13: Change first of ItemTouchHelper.SimpleCallback argument to include all directions

    //TODO 14: Get the original and target index of the card you are moving

    //TODO 15: Call Collections.swap() to swap the items in the dataset (i.e photos)

    //TODO 16: Notify the adapter of the card item's movement, and update it on the
    // positions, the old and new and return true

    /**
     * Implement Swipe to delete an item in the onSwiped method
     * **/

    //TODO 10: Implement swipe left or right to delete a card item in the onSwipe

    //TODO 11: Notify RecyclerView after deleting an item to animate it

    //TODO 12: Add ItemTouchHelper instance to the RecyclerView

  }
}
