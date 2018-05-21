package com.target.dealbrowserpoc.dealbrowser.component;

import com.target.dealbrowserpoc.dealbrowser.fragments.DealsListFragment;
import com.target.dealbrowserpoc.dealbrowser.modules.DealsModule;
import com.target.dealbrowserpoc.dealbrowser.modules.NetworkModule;

import dagger.Component;

/**
 * Created by yashwantsingh on 21/05/18.
 */

@Component(modules = {NetworkModule.class, DealsModule.class})
public interface DealApplicationComponent {
    void inject(DealsListFragment o);
}
