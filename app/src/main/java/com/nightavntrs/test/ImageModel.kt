package com.nightavntrs.test

/**
 * Created by Parsania Hardik on 03-Jan-17.
 */
class ImageModel {

    var name: String? = null
    var image_drawable: Int = 0
    var id: Int = 0

    fun getNames(): String {
        return name.toString()
    }

    fun setNames(name: String) {
        this.name = name
    }

    fun setIDs(ID: Int) {
        this.id = ID
    }

    fun getIDs(): Int {
        return id
    }

    fun getImage_drawables(): Int {
        return image_drawable
    }

    fun setImage_drawables(image_drawable: Int) {
        this.image_drawable = image_drawable
    }

}