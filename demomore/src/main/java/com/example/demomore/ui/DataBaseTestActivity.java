package com.example.demomore.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.demomore.BaseActivity;
import com.example.demomore.R;
import com.example.demomore.adapter.ContactAdapter;
import com.example.demomore.database.ContactDBHelper;
import com.example.demomore.entity.ContactEnity;
import com.example.demomore.utils.DataBaseUtils;
import com.example.demomore.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;

public class DataBaseTestActivity extends BaseActivity {


    @Bind(R.id.btn_database_debug_address_get)
    Button btnDatabaseDebugAddressGet;
    @Bind(R.id.btn_add_new)
    Button btnAddNew;
    @Bind(R.id.btn_delete)
    Button btnDelete;
    @Bind(R.id.btn_updata)
    Button btnUpdata;

    @Bind(R.id.tv_mesage)
    TextView tvMesage;
    @Bind(R.id.btn_query_all)
    Button btnQueryAll;
    @Bind(R.id.btn_query_keyword)
    Button btnQueryKeyword;
    @Bind(R.id.rv_content)
    RecyclerView rvContent;


    private ContactDBHelper mContactDBHelper;
    private ArrayList<String> allContentName;
    private int mContentSize;
    private ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    protected void initListener() {
        btnDatabaseDebugAddressGet.setOnClickListener(this);
        btnAddNew.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdata.setOnClickListener(this);
        btnQueryAll.setOnClickListener(this);
        btnQueryKeyword.setOnClickListener(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvContent.setLayoutManager(linearLayoutManager);

        contactAdapter = new ContactAdapter(R.layout.item_contact_adapter, null);

        //设置动画
        contactAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rvContent.setAdapter(contactAdapter);
        rvContent.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
                mContactDBHelper.deleteContact(Integer.parseInt(mContactList.get(position).getId()));

                ToastUtils.showToastShort("id为  "+mContactList.get(position).getId()+"  的数据删除成功");
            }
        });


    }

    @Override
    protected void initData() {
        //创建对应的数据库
        mContactDBHelper = new ContactDBHelper(getApplicationContext());
        if (mContactDBHelper.count() == 0) {
            for (int i = 0; i < 10; i++) {
                String name = "name_zbc_" + i;
                String phone = "phone_" + i;
                String email = "email_" + i;
                String street = "street_" + i;
                String place = "place_北京_" + i;
                mContactDBHelper.insertContact(name, phone, email, street, place);
            }
        }
    }

    @Override
    public int getContentView() {
        return R.layout.activity_data_base_test;
    }

    @Override
    public void onClick(View view) {
        allContentName = mContactDBHelper.getAllCotacts();
        mContentSize = allContentName.size();
        switch (view.getId()) {
            case R.id.btn_database_debug_address_get:
                //获取本地数据库调试的网络地址
                DataBaseUtils.PrintDebugDBAddressLog();
                break;
            case R.id.btn_add_new:

                mContactDBHelper.insertContact("周本成" + mContentSize, "15210187720" + mContentSize, "1107472286" + mContentSize, "芙蓉街" + mContentSize, "海淀区" + mContentSize);
                ToastUtils.showToastShort("数据新增成功");
                break;
            case R.id.btn_delete:
                Cursor cursor1 = mContactDBHelper.queryAll();
                if (cursor1 != null) {
                    if (cursor1.getCount() > 0) {
                        cursor1.moveToLast();
                        int anInt = cursor1.getInt(cursor1.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_ID));
                        mContactDBHelper.deleteContact(anInt);
                        ToastUtils.showToastShort("数据删除成功");

                    } else {

                        ToastUtils.showToastShort("已经没有新数据了哦");
                    }
                    cursor1.close();
                }


                break;
            case R.id.btn_updata:
                mContactDBHelper.updateContact(mContentSize, "周本成" + mContentSize + "new", "15210187720" + mContentSize + "new",
                        "1107472286" + mContentSize + "new", "芙蓉街" + mContentSize + "new", "海淀区" + mContentSize + "new");
                ToastUtils.showToastShort("数据修改成功");
                break;
            case R.id.btn_query_keyword:

                ToastUtils.showToastShort("数据查询成功");
                break;
            case R.id.btn_query_all:
                mContactList.clear();
                Cursor cursor = mContactDBHelper.queryAll();
                if (cursor != null && cursor.getCount() > 0) {
                    do {
                        String id = cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_ID));
                        String name = cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_NAME));
                        String phone = cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_PHONE));
                        String email = cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_EMAIL));
                        String street = cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_STREET));
                        String city = cursor.getString(cursor.getColumnIndex(ContactDBHelper.CONTACTS_COLUMN_CITY));

                        mContactList.add(new ContactEnity(id, name, phone, email, street, city));
                    }while (cursor.moveToNext());
                    contactAdapter.setNewData(mContactList);
                }

                if (cursor != null) {
                    cursor.close();
                }

                ToastUtils.showToastShort("数据查询成功");
                break;

        }
    }

    ArrayList<ContactEnity> mContactList = new ArrayList();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContactDBHelper.close();
    }
}
