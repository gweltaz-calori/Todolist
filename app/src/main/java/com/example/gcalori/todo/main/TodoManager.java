package com.example.gcalori.todo.main;

import java.util.ArrayList;

/**
 * Created by gcalori on 26/03/2018.
 */

public final class TodoManager {

    // L'utilisation du mot clé volatile, en Java version 5 et supérieure,
    // permet d'éviter le cas où "Singleton.instance" est non nul,
    // mais pas encore "réellement" instancié.
    // De Java version 1.2 à 1.4, il est possible d'utiliser la classe ThreadLocal.
    private static volatile TodoManager instance = null;

    private ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructeur de l'objet.
     */
    private TodoManager() {
        // La présence d'un constructeur privé supprime le constructeur public par défaut.
        // De plus, seul le singleton peut s'instancier lui-même.
        super();
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance du singleton.
     */
    public final static TodoManager getInstance() {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (TodoManager.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(TodoManager.class) {
                if (TodoManager.instance == null) {
                    TodoManager.instance = new TodoManager();
                }
            }
        }
        return TodoManager.instance;
    }


}
