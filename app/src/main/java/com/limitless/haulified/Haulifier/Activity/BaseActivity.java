package com.limitless.haulified.Haulifier.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.auth0.android.jwt.Claim;
import com.auth0.android.jwt.JWT;
import com.limitless.haulified.Haulifier.R;
import com.limitless.haulified.Haulifier.common.CustomDialog;
import com.limitless.haulified.Haulifier.common.Preference;
import com.limitless.haulified.Haulifier.model.SignInModel;
import com.limitless.haulified.Haulifier.utils.CircularNetworkImageView;

/**
 * Created by apta on 5/22/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public LinearLayout llBody;
    public LayoutInflater inflater;

    public ProgressDialog progressdialog;

    public Preference preference;
    Preference prefe1;

    public static float px;

    public CustomDialog customDialog;

    public DrawerLayout drawer;

    public LinearLayout scountCircleLayout;

    public LinearLayout llToolBarTitle;

    public TextView tvToolBarTitle1, tvToolBarTitle2;

    public TextView sCircleTextView;

    public View cartView;

    private Toolbar toolbar, toolbar1, toolbar2;

    private LinearLayout lltrucklist,lluserlist,llsetting,llterms,llprivacy,llsupport,llabout1;

    private ImageView ivDiv, ivDiv1, ivArrow, divRepair, divRepairList;

    private Menu mMenu;
    public  MenuItem item3;
    Animation animFadein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base);
       // Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(BaseActivity.this));
        initializeView();


        Resources r = getResources();
        px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, r.getDisplayMetrics());

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        initialize();
        preference  =new Preference(BaseActivity.this);
        prefe1 =new Preference(BaseActivity.this);
        toolbar1 = (Toolbar) findViewById(R.id.toolbar);
        toolbar2 = (Toolbar) findViewById(R.id.toolbar1);


    }



    private void initializeView() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        inflater = getLayoutInflater();
        llBody = (LinearLayout) findViewById(R.id.llBody);

        llToolBarTitle = (LinearLayout) findViewById(R.id.llToolBarTitle);
        tvToolBarTitle1 = (TextView) findViewById(R.id.tvToolBarTitle1);
        tvToolBarTitle2 = (TextView) findViewById(R.id.tvToolBarTitle2);
        lltrucklist = (LinearLayout) findViewById(R.id.lltrucklist);
        lluserlist = (LinearLayout) findViewById(R.id.lluserlist);
        llsetting = (LinearLayout) findViewById(R.id.llsetting);

    }

    public abstract void initialize();

    @Override
    protected void onResume() {
        super.onResume();

if(BaseActivity.this instanceof TruckerList){
    setupMenu();
}
        else if (BaseActivity.this instanceof Settings ||BaseActivity.this instanceof DeliveriesActivity  ||BaseActivity.this instanceof UserList) {
            setupMenu1();
        }
       else if (BaseActivity.this instanceof UserDetails||BaseActivity.this instanceof DeliveryItemDetails||BaseActivity.this instanceof OfferQuote1 || BaseActivity.this instanceof CommisionCalcutaor ) {
            setupMenu1();
        }
 /*else if (BaseActivity.this instanceof Terms||BaseActivity.this instanceof PickDriverNameScreen||BaseActivity.this instanceof Privacy || BaseActivity.this instanceof Support || BaseActivity.this instanceof AboutUs) {
    setupMenu1();
}
else if ( BaseActivity.this instanceof Inventory|| BaseActivity.this instanceof ForgetPassword || BaseActivity.this instanceof ForgetPasswordFinal) {
    setupMenu2();
}*/
    }

    private void setupMenu() {

        toolbar = toolbar1;
        toolbar1.setVisibility(View.VISIBLE);
        toolbar2.setVisibility(View.GONE);

        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(0);
        setSupportActionBar(toolbar);
        ((AppCompatActivity) this).getSupportActionBar().setTitle("Trucker");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        View header = navigationView.getHeaderView(0);
        setNavigationHeaderValue(header);
        lltrucklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, TruckerList.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
        lluserlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, UserList.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Settings.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });

      /*  llterms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Terms.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Privacy.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Support.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llabout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, AboutUs.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });*/

    }

    public void setupMenu1() {
        toolbar = toolbar2;
        toolbar1.setVisibility(View.GONE);
        toolbar2.setVisibility(View.VISIBLE);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(0);
        setSupportActionBar(toolbar);
        ((AppCompatActivity) this).getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        View header = navigationView.getHeaderView(0);
        setNavigationHeaderValue(header);
        lltrucklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, TruckerList.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
        lluserlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, UserList.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Settings.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });

        /*llterms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Terms.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Privacy.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Support.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llabout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, AboutUs.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });*/
    }
    private void setupMenu2() {
        toolbar = toolbar2;
        toolbar1.setVisibility(View.GONE);
        toolbar2.setVisibility(View.VISIBLE);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbar.getLayoutParams();
        params.setScrollFlags(0);
        setSupportActionBar(toolbar);
        ((AppCompatActivity) this).getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        View header = navigationView.getHeaderView(0);
        lltrucklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, TruckerList.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        lluserlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, UserList.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Settings.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llterms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Terms.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Privacy.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, Support.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
        llabout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BaseActivity.this, AboutUs.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        this.mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
       final MenuItem item = menu.findItem(R.id.action_cart);
        final MenuItem item2 = menu.findItem(R.id.action_search);
        item3 = menu.findItem(R.id.action_submit);

         if ( BaseActivity.this instanceof DeliveriesActivity) {
             item3.setVisible(false);
            item.setVisible(false);
            item2.setVisible(false);
        }
        if ( BaseActivity.this instanceof DeliveryItemDetails) {
            item3.setVisible(false);
            item.setVisible(false);
            item2.setVisible(false);
        }

        else if ( BaseActivity.this instanceof UserDetails ||BaseActivity.this instanceof TruckerList || BaseActivity.this instanceof CommisionCalcutaor|| BaseActivity.this instanceof Settings|| BaseActivity.this instanceof UserList) {
            item3.setVisible(false);
            item2.setVisible(false);
            item.setVisible(false);

            item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return false;
                }
            });
            item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                   // Toast.makeText(BaseActivity.this,"uu",Toast.LENGTH_SHORT).show();
                    return false;
                }
            });

        }
        else if ( BaseActivity.this instanceof OfferQuote1) {
            item3.setVisible(false);
            item2.setVisible(false);
            item.setVisible(false);

        }

          return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        /*if (id == R.id.nav_llNewOrder) {
            Toast.makeText(BaseActivity.this, "In Progress.", Toast.LENGTH_SHORT).show();
        }*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setMenuTitle(String str) {
        try {
            ((AppCompatActivity) this).getSupportActionBar().setTitle(str);
        } catch (Exception e) {
            setTitle(str);
        }
    }

    /**
     * This method is to show the loading progress dialog when some other
     * functionality is taking place.
     **/
    public void showLoader(String msg) {
        runOnUiThread(new RunShowLoader(msg, ""));
    }

    // This is to show the loading progress dialog when some other functionality
    // is taking place.






    class RunShowLoader implements Runnable {
        private String strMsg;
        private String title;

        public RunShowLoader(String strMsg, String title) {
            this.strMsg = strMsg;
            this.title = title;
        }

        @Override
        public void run() {
            try {
                if (progressdialog == null
                        || (progressdialog != null && !progressdialog
                        .isShowing())) {
                    progressdialog = ProgressDialog.show(BaseActivity.this,
                            title, strMsg);
                } else if (progressdialog == null
                        || (progressdialog != null && progressdialog
                        .isShowing())) {
                    progressdialog.setMessage(strMsg);
                }
            } catch (Exception e) {
                progressdialog = null;
            }
        }
    }


    /**
     * For hiding progress dialog (Loader ).
     **/
    public void hideLoader() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (progressdialog != null && progressdialog.isShowing())
                        progressdialog.dismiss();
                    progressdialog = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Method to Show the alert dialog
     **/
    public void showCustomDialog(Context context, String strTitle,
                                 String strMessage, String firstBtnName, String secondBtnName,
                                 String from) {
        runOnUiThread(new RunshowCustomDialogs(context, strTitle, strMessage,
                firstBtnName, secondBtnName, from, false, false));
    }

    // For showing Dialog message.
    class RunshowCustomDialogs implements Runnable {
        private String strTitle;// Title of the dialog
        private String strMessage;// Message to be shown in dialog
        private String firstBtnName;
        private String secondBtnName;
        private String from;
        private boolean isCancelable = false;
        private View.OnClickListener posClickListener;
        private View.OnClickListener negClickListener;

        public RunshowCustomDialogs(Context context, String strTitle,
                                    String strMessage, String firstBtnName, String secondBtnName,
                                    String from, boolean isCancelable, boolean isCheckin) {
            this.strTitle = strTitle;
            this.strMessage = strMessage;
            this.firstBtnName = firstBtnName;
            this.secondBtnName = secondBtnName;
            this.isCancelable = isCancelable;

            if (from != null)
                this.from = from;
            else
                this.from = "";

        }

        @Override
        public void run() {
            if (customDialog != null && customDialog.isShowing())
                customDialog.dismiss();
            View view = inflater.inflate(R.layout.custom_common_popup, null);
            customDialog = new CustomDialog(BaseActivity.this, view,
                    preference.getIntFromPreference(
                            Preference.DEVICE_DISPLAY_WIDTH, 320) - 40,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            customDialog.setCancelable(isCancelable);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitlePopup);
            TextView tvMessage = (TextView) view
                    .findViewById(R.id.tvMessagePopup);
            final Button btnYes = (Button) view.findViewById(R.id.btnYesPopup);
            final Button btnNo = (Button) view.findViewById(R.id.btnNoPopup);

            tvTitle.setText("" + strTitle);
            tvMessage.setText("" + strMessage);
            btnYes.setText("" + firstBtnName);

            if (secondBtnName != null && !secondBtnName.equalsIgnoreCase(""))
                btnNo.setText("" + secondBtnName);
            else
                btnNo.setVisibility(View.GONE);

            if (posClickListener == null)
                btnYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        animFadein = AnimationUtils.loadAnimation(BaseActivity.this, R.anim.fade_in);
                        btnYes.startAnimation(animFadein);
                        customDialog.dismiss();
                        onButtonYesClick(from);
                    }
                });
            else
                btnYes.setOnClickListener(posClickListener);

            if (negClickListener == null)
                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        animFadein = AnimationUtils.loadAnimation(BaseActivity.this, R.anim.fade_in);
                        btnNo.startAnimation(animFadein);
                        customDialog.dismiss();
                        onButtonNoClick(from);
                    }
                });
            else
                btnNo.setOnClickListener(negClickListener);

            try {
                if (!customDialog.isShowing())
                    customDialog.show();
            } catch (Exception e) {
            }
        }
    }

    public void onButtonYesClick(String from) {

    }

    public void onButtonNoClick(String from) {

    }
    public void saveToken(SignInModel signInModel) {
        preference.saveStringInPreference(Preference.token, signInModel.getToken());
        preference.commitPreference();
    }
    private void setNavigationHeaderValue(View header) {
        TextView userName = (TextView) header.findViewById(R.id.userNameHeader);
        TextView accountHeader = (TextView) header.findViewById(R.id.accountHeader);
        ImageView user_Image = (CircularNetworkImageView) header.findViewById(R.id.userImageViewheader);
        LinearLayout llNavHeader = (LinearLayout) header.findViewById(R.id.llNavHeader);
       String token = prefe1.getStringFromPreference(Preference.token, "");
        JWT jwt = new JWT(token);
        Claim subscriptionMetaData = jwt.getClaim("email");
        String account_name = subscriptionMetaData.asString();

        Claim subscriptionMetaData1 = jwt.getClaim("username");
        String user_name = subscriptionMetaData1.asString();
        //userName.setText("Welcome " + user_name);

        accountHeader.setText(/*"@ "+*/account_name);
    }
    public void ClearToke() {
        preference.saveStringInPreference(Preference.token, "");
        preference.commitPreference();
    }

}
