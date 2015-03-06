package br.com.planejamentoagro.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.planejamentoagro.R;
import br.com.planejamentoagro.view.SlidingTabLayout;

public class SlidingFragment extends Fragment {

	private SlidingTabLayout mSlidingTabLayout;
	private ViewPager mViewPager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.activity_main, container,false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
		mViewPager.setAdapter(new Adapt(getActivity().getSupportFragmentManager()));		
		
		mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
		mSlidingTabLayout.setViewPager(mViewPager);
		
	}
	class Adapt extends FragmentPagerAdapter{
		private final String[] TITLES = {"Cliente", "Talhões"};
		public Adapt(android.support.v4.app.FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}



		@Override
		public Fragment getItem(int arg0) {
			return ListaClientesFragment();
		}
		
	}
	class Adaptador extends PagerAdapter{
		private final String[] TITLES = {"Cliente", "Talhões"};
		@Override
		public int getCount() {
			return TITLES.length;
		}

		@Override
		public boolean isViewFromObject(View view, Object o) {
			// TODO Auto-generated method stub
			return o == view;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}
        @Override
		public Object instantiateItem(ViewGroup container, int position) {
//            // Inflate a new layout from our resources
//            View view = getActivity().getLayoutInflater().inflate(R.layout.pager_item,
//                    container, false);
//            // Add the newly created View to the ViewPager
//            container.addView(view);
// 
//            // Retrieve a TextView from the inflated View, and update it's text
//            TextView title = (TextView) view.findViewById(R.id.item_title);
//            title.setText(String.valueOf(position + 1));
// 
//            // Return the View
//            return view;
        	return new ListaTalhaoComAplicacaoFragment();
        }
        @Override
		public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
		
	}
	public Fragment ListaClientesFragment() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
