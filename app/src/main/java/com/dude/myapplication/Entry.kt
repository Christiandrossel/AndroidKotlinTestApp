package com.dude.myapplication

class Entry {
    var id: Int? = 0
    var image: Int? = 0
    var title: String? = null
    var description: String? = null

    constructor(id: Int?, image: Int?, title: String?, description: String?) {
        this.id = id
        this.image = image
        this.title = title
        this.description = description
    }

    constructor(id: Int?, title: String?, description: String?) {
        this.id = id
        this.title = title
        this.description = description
    }

    constructor(entry: Entry){
        this.id=entry.id
        this.title=entry.title
        this.description=entry.description
        if(entry.image != null)
            this.image = entry.image
    }


}