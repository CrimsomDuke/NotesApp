package com.example.notesapp.db

import com.example.notesapp.model.ColorOptions
import com.example.notesapp.model.Note

object FakeDB {
    public var notes = arrayListOf<Note>(
        Note("Go Shopping", "Buy milk, eggs, bread, and cheese"),
        Note("Walk the dog", "Take the dog for a walk in the park", ColorOptions.colors[1]),
        Note("Wash the car", "Wash the car and clean the interior", ColorOptions.colors[2]),
        Note("Do the laundry", "Wash, dry, and fold the laundry", ColorOptions.colors[3]),
        Note("Clean the house", "Vacuum, dust, and mop the floors", ColorOptions.colors[4]),
        Note("Mow the lawn", "Mow the front and back lawns", ColorOptions.colors[5]),
        Note("Water the plants", "Water the indoor and outdoor plants", ColorOptions.colors[6]),
        Note("Take out the trash", "Take the trash out to the curb", ColorOptions.colors[7]),
        Note("Pick up the kids", "Pick up the kids from school", ColorOptions.colors[8]),
        Note("Go to the gym", "Lift weights and run on the treadmill", ColorOptions.colors[9]),
    )
}