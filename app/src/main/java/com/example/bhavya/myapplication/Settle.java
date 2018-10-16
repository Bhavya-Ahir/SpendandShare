package com.example.bhavya.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class Settle extends AppCompatActivity {

    static ArrayList<String> name = new ArrayList<String>();
    static ArrayList<Double> paid = new ArrayList<Double>();
    public DatabaseReference groupdatabaseref;

    public DatabaseReference myDatabase;
    public String groupName;
    private TextView btype;
    @Override
    public void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        name = intent.getStringArrayListExtra("name");
        paid = (ArrayList<Double>) getIntent().getSerializableExtra("paid");
        //  share();

        btype = (TextView) findViewById(R.id.billType);
        groupName = getIntent().getStringExtra("Group Name");

        groupdatabaseref = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("GROUP: " + groupName).child("Members");

        for (int i = 0; i < Settle.name.size(); i++) {
            groupdatabaseref.child(Settle.name.get(i)).setValue(Settle.paid.get(i));
        }


        myDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("GROUP: " + groupName).child("_BILLS").child("BILL 1 : " + createGroup.spinner.getSelectedItem().toString());

        Group p=new Group();
        p.createPerson();

        p.calculateBalance();

        btype.setText(createGroup.spinner.getSelectedItem().toString());

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
        HashMap<String, Double> dataMap = new HashMap<>();
        private int count = 0;
        private double bill = 0;

        public double getBill() {
            return bill;
        }

        public void setBill(double bill) {
            this.bill = bill;
        }
        public  void createPerson() {
            for (int i = 0; i < Settle.name.size(); i++) {
                list.add(new Person(Settle.name.get(i), Settle.paid.get(i), 0));
                //dataMap.put(Person.class);
                //dataMap.put("Amount paid " + i, Settle.paid.get(i));
                Log.i("grp name", groupName);
                myDatabase.child(Settle.name.get(i)).child("BILL PAID ").setValue(Settle.paid.get(i));


                bill = bill + Settle.paid.get(i);
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
                    myDatabase.child(Settle.name.get(i)).child("transaction").child(list.get(i).getName()).setValue(0);
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
                    myDatabase.child(x.getName()).child("transaction").child("i paid to "+y.getName()).setValue(-x.getBalance());
                    myDatabase.child(y.getName()).child("transaction").child("i received from "+x.getName()).setValue(x.getBalance());
                    System.out.println(message.add(x.getName() + " will pay Rs :" + x.getBalance() + " to " + y.getName()));
                    y.setBalance(y.getBalance() + x.getBalance());//updating negative list
                    x.setBalance(0);
                    i++;
                    // break outer;
                }
                if (x.getBalance() > (-1 * y.getBalance())) {
                    myDatabase.child(x.getName()).child("transaction").child("i paid to "+y.getName()).setValue(-x.getBalance());
                    myDatabase.child(y.getName()).child("transaction").child("i received from "+x.getName()).setValue(x.getBalance());
                    System.out.println(message.add(x.getName() + " will pay Rs:" + (-1 * y.getBalance()) + " to " + y.getName()));
                    x.setBalance(x.getBalance() + y.getBalance());//updating positive list
                    y.setBalance(0);
                    j++;
                    //break outer;
                }

                if (x.getBalance() + y.getBalance() == 0) {
                    myDatabase.child(x.getName()).child("transaction").child("i paid to "+y.getName()).setValue(-x.getBalance());
                    myDatabase.child(y.getName()).child("transaction").child("i received from "+x.getName()).setValue(x.getBalance());
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
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Settle.this,
                    android.R.layout.simple_list_item_1, android.R.id.text1,message);
            listView.setAdapter(adapter);

        }

    }
}
