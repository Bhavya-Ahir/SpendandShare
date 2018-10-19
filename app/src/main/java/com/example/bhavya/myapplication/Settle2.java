package com.example.bhavya.myapplication;

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

public class Settle2 extends AppCompatActivity {
    static ArrayList<Lists_for_bills> newBillList;
    static DatabaseReference myDatabase1;
    static String groupName;
    private TextView btype1;
    public ListView listView1;
    private DatabaseReference gref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settle2);
        Bundle bundleObject = getIntent().getExtras();
        btype1 = (TextView) findViewById(R.id.bill_1);
        //Toast.makeText(Settle2.this, "inside settle 2", Toast.LENGTH_LONG).show();
        groupName = getIntent().getStringExtra("Group Name");
        newBillList = (ArrayList<Lists_for_bills>) bundleObject.getSerializable("listItems");
        gref = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("GROUP " + groupName).child("Group History");

        createGroup.x=createGroup.x+1;
        myDatabase1 = FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("GROUP " + groupName).child("_BILLS").child("BILL "+createGroup.x+" : "+Add_Bill.spinner1.getSelectedItem().toString());

        Settle2.Group p = new Settle2.Group();
        p.createPerson();

        p.calculateBalance();
        btype1.setText(Add_Bill.spinner1.getSelectedItem().toString());
    }

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

        ArrayList<String> message = new ArrayList<>();
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

        public void createPerson() {
            for (int i = 0; i < Settle.name.size(); i++) {
                list.add(new Person(Settle2.newBillList.get(i).getName(), Double.parseDouble(Settle2.newBillList.get(i).getAmt()), 0));

                Log.i("grp name", Settle2.groupName);

                myDatabase1.child(Settle2.newBillList.get(i).getName()).child("BILL PAID ").setValue(Double.parseDouble(Settle2.newBillList.get(i).getAmt()));


                bill = bill + Double.parseDouble(Settle2.newBillList.get(i).getAmt());
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
                    myDatabase1.child(Settle2.newBillList.get(i).getName()).child("transaction").child(list.get(i).getName()).setValue(0);
                    System.out.println(message.add(list.get(i).getName() + " needs to pay Rs:0 "));
                    gref.child(message.toString());

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
                    myDatabase1.child(x.getName()).child("transaction").child("i paid to " + y.getName()).setValue(-x.getBalance());
                    myDatabase1.child(y.getName()).child("transaction").child("i received from " + x.getName()).setValue(x.getBalance());
                    System.out.println(message.add(x.getName() + " will pay Rs :" + x.getBalance() + " to " + y.getName()));

                    gref.child(message.toString());
                    y.setBalance(y.getBalance() + x.getBalance());//updating negative list
                    x.setBalance(0);
                    i++;
                    // break outer;
                }
                if (x.getBalance() > (-1 * y.getBalance())) {
                    myDatabase1.child(x.getName()).child("transaction").child("i paid to " + y.getName()).setValue(-x.getBalance());
                    myDatabase1.child(y.getName()).child("transaction").child("i received from " + x.getName()).setValue(x.getBalance());
                    System.out.println(message.add(x.getName() + " will pay Rs:" + (-1 * y.getBalance()) + " to " + y.getName()));
                    gref.child(message.toString());

                    x.setBalance(x.getBalance() + y.getBalance());//updating positive list
                    y.setBalance(0);
                    j++;
                    //break outer;
                }

                if (x.getBalance() + y.getBalance() == 0) {
                    myDatabase1.child(x.getName()).child("transaction").child("i paid to " + y.getName()).setValue(-x.getBalance());
                    myDatabase1.child(y.getName()).child("transaction").child("i received from " + x.getName()).setValue(x.getBalance());
                    System.out.println(message.add(x.getName() + " will pay Rs :" + x.getBalance() + " to " + y.getName()));
                    gref.child(message.toString());

                    x.setBalance(0);
                    y.setBalance(0);
                    i++;
                    j++;
                    //break outer;
                }

            }

            Log.d("abc", message.toString());
            listView1 = (ListView) findViewById(R.id.list1);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(Settle2.this,
                    android.R.layout.simple_list_item_1, message);
            listView1.setAdapter(adapter);

        }


    }

}