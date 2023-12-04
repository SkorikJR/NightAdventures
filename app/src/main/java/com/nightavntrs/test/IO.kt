package com.nightavntrs.test

import android.content.Context
import android.util.Log
import java.io.File
import java.io.FileWriter

class IO {
     fun saveFile(app: Context, filename: String, body: String) {
        try {
            val file = File(app.getFilesDir(), filename)
            val writer = FileWriter(file)
            writer.append(body)
            writer.flush()
            writer.close()

            // `requestPermission` completed successfully and the user has accepted permission
            Log.i("[IO]", "File writed: " + filename)
            //Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("[IO]", "Erorr writing file: " + filename)
            //Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
    }
    fun saveFileFromClass(app: Context, filename: String, body: String) {
        try {
            val file = File(app.getFilesDir(), filename)
            val writer = FileWriter(file)
            writer.append(body)
            writer.flush()
            writer.close()
            Log.d("[i]", "File writed: " + filename)
            //Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("[!]", "Erorr writing file: " + filename)
            //Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
        }
    }
}