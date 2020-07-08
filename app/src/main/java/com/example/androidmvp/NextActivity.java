package com.example.androidmvp;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class NextActivity extends AppCompatActivity {

    ArrayList<String> list=new ArrayList<String>();
    private RecyclerView listView;
    private RecycleViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next);
        listView=findViewById(R.id.recyclerview);
        for (int i=0;i<30;i++) {
            list.add("item" + i);
        }
        String s="abccccdd";
        int result=0;
        countAndSay(3);
        isHappy(19);
        generateNext(5);
        numWays(46);
        int [] init={9,10,11,12,13};
        isStraight(init);
        HashMap<Character,String> map=new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                String ss=map.get(s.charAt(i));
                map.put(s.charAt(i),map.get(s.charAt(i))+"1");
                String str=map.get(s.charAt(i));
                int n=str.length();
                if(map.get(s.charAt(i)).length()%2==0)result+=result;
            }else{
                map.put(s.charAt(i),"1");
            }
        }
        adapter=new RecycleViewAdapter(this, list, new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {

            }
        });
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.addItemDecoration(new RecycleDecorationLinear(ContextCompat.getDrawable(this, R.drawable.dottedline)));
        listView.setAdapter(adapter);
    }
    public boolean isStraight(int[] nums) {
        // int dump=nums[0];
        int min=0;
        int max=0;
        // for(int i=1;i<nums.length;i++){
        //     if(dump==nums[i] && [i]!=0){
        //         return false;
        //     }
        // }
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]!=0){
                min=Math.min(nums[i],nums[i+1]);
                if(nums[i]==nums[i+1]){
                    return false;
                }
            }
        }
        if(min==0){
            return true;
        }else{
            return nums[nums.length-1]-min==4?true:false;
        }
    }
    public int numWays(int n) {
        if(n==0){
            return 1;
        }
        if(n==1){
            return 1;
        }
        if(n==2){
            return 2;
        }
        int n1=1;
        int n2=2;
        int dump=0;
        for(int i=3;i<=n;i++){
            dump=n2;
            n2=n1+n2;
            n1=dump;
        }
        return n2%1000000007;
    }
    public List<List<Integer>> generateNext(int numRows) {
        List<List<Integer>> list=new ArrayList<List<Integer>>();
        if(numRows==0){
            return list;
        }
        if(numRows==1){
            List<Integer> one=new ArrayList<>();
            one.add(1);
            list.add(one);
        }
        if(numRows==2){
            List two=new ArrayList<Integer>();
            two.add(1);
            two.add(1);
            List<Integer> one=new ArrayList<>();
            one.add(1);
            list.add(one);
            list.add(two);
        }
        list=generateNext(numRows-1);
        List result=new ArrayList<Integer>();
        for(int i=0;i<numRows-2;i++){
            result.add(list.get(list.size()-1).get(i)+list.get(list.size()-1).get(i+1));
        }
        result.add(0,1);
        result.add(1);
        list.add(result);
        return list;
    }
    public String countAndSay(int n) {
        StringBuilder sb=new StringBuilder();
        if(n==1){
            return "1";
        }
        String str=countAndSay(n-1);
        int start=0;
        int curr=1;
        for(curr=1;curr<str.length();curr++){
            if(str.charAt(start)!=str.charAt(curr)){
                sb.append(curr-start).append(str.charAt(start));
                start=curr;
            }
        }
        if(curr!=start){
            sb.append(curr-start).append(str.charAt(start));
        }
        return sb.toString();
    }
    public boolean isHappy(int n) {
        int result=0;
        while(n>0){
            result+=Math.pow(n%10,2);
            n/=10;
        }
        if(result==1){
            return true;
        }else{
            isHappy(result);
        }
        return false;

    }
}
class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) == 0)
            outRect.top = space;
    }
}