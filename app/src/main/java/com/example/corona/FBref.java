package com.example.corona;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * FBref.
 * @author Liad Peretz
 * @version	1.0
 * @since 16/4/2021
 * Short description- FBref.
 */
public class FBref {
    public static FirebaseDatabase FBDB = FirebaseDatabase.getInstance();
    public static DatabaseReference refUsers=FBDB.getReference("Users");
}
