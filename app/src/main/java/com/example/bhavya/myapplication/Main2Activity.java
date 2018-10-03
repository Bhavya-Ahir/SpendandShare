package com.example.bhavya.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Main2Activity extends AppCompatActivity {

    static ArrayList<String> name = new ArrayList<String>();
    static ArrayList<Double> paid = new ArrayList<Double>();
    public DatabaseReference myDatabase;

    @Override
    public void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        name = intent.getStringArrayListExtra("name");
        paid = (ArrayList<Double>) getIntent().getSerializableExtra("paid");
        //  share();
        myDatabase= FirebaseDatabase.getInstance().getReference();
        Group p=new Group();
        p.createPerson();
        p.calculateBalance();
    }

    //public void share(){
    class Person {
        private String name;
        private double paid;
        private double balance;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public Person(String name, double paid, double b) {
            this.name = name;
            this.paid = paid;
            this.balance = b;

        }

        public Person() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            name = name;
        }

        public double getPaid() {
            return paid;
        }

        public void setPaid(double paid) {
            this.paid = paid;
        }
    }

    class Group extends Person {

        ArrayList<String> message=new ArrayList<>();
        ArrayList<Person> list = new ArrayList<Person>();
        ArrayList<Person> plist = new ArrayList<Person>();
        ArrayList<Person> nlist = new ArrayList<Person>();
        HashMap<String,String> dataMap=new HashMap<>();
        private int count = 0;
        private double bill = 0;

        public double getBill() {
            return bill;
        }

        public void setBill(double bill) {
            this.bill = bill;
        }
        public  void createPerson() {
            for (int i = 0; i < Main2Activity.name.size(); i++) {
                list.add(new Person(Main2Activity.name.get(i), Main2Activity.paid.get(i), 0));
                dataMap.put("UserName",Main2Activity.name.get(i));
                dataMap.put("Amount paid",Main2Activity.paid.get(i).toString());
                myDatabase.push().setValue(dataMap);
                bill = bill + Main2Activity.paid.get(i);
                count++;
            }
        }
        // list.add(new Person(n, r,0));
        //count++;


        public void calculateBalance() {
            bill = bill / count;

            for (int i = 0; i < list.size(); i++) {
                list.get(i).setBalance(bill - list.get(i).getPaid());

                if (list.get(i).getBalance() == 0) {
                    System.out.println(message.add(list.get(i).getName() + " needs to pay Rs:0 "));
                }
                if (list.get(i).getBalance() > 0) {
                    plist.add(list.get(i));
                }
                if (list.get(i).getBalance() < 0) {
                    nlist.add(list.get(i));
                }
            }
            finalCalculations();

        }


        public void finalCalculations() {
            int i = 0, j = 0;
            outer:
            while (i < plist.size() && j < nlist.size()) {
                // System.out.println("*");
                Person x = plist.get(i);
                Person y = nlist.get(j);
                if ((-1 * y.getBalance()) > x.getBalance()) {
                    System.out.println(message.add(x.getName() + " will pay Rs :" + x.getBalance() + " to " + y.getName()));
                    y.setBalance(y.getBalance() + x.getBalance());//updating negative list
                    x.setBalance(0);
                    i++;
                    // break outer;
                }
                if (x.getBalance() > (-1 * y.getBalance())) {
                    System.out.println(message.add(x.getName() + " will pay Rs:" + (-1 * y.getBalance()) + " to " + y.getName()));
                    x.setBalance(x.getBalance() + y.getBalance());//updating positive list
                    y.setBalance(0);
                    j++;
                    //break outer;
                }

                if (x.getBalance() + y.getBalance() == 0) {

                    System.out.println(message.add(x.getName() + " will pay Rs :" + x.getBalance() + " to " + y.getName()));
                    x.setBalance(0);
                    y.setBalance(0);
                    i++;
                    j++;
                    //break outer;
                }

            }
            ListView listView ;
            listView = (ListView) findViewById(R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Main2Activity.this,
                    android.R.layout.simple_list_item_1, android.R.id.text1,message);
            listView.setAdapter(adapter);

        }

    }
}
