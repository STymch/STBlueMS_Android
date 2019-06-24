/*
 * Copyright (c) 2019  STMicroelectronics – All rights reserved
 * The STMicroelectronics corporate logo is a trademark of STMicroelectronics
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * - Redistributions of source code must retain the above copyright notice, this list of conditions
 *   and the following disclaimer.
 *
 * - Redistributions in binary form must reproduce the above copyright notice, this list of
 *   conditions and the following disclaimer in the documentation and/or other materials provided
 *   with the distribution.
 *
 * - Neither the name nor trademarks of STMicroelectronics International N.V. nor any other
 *   STMicroelectronics company nor the names of its contributors may be used to endorse or
 *   promote products derived from this software without specific prior written permission.
 *
 * - All of the icons, pictures, logos and other images that are provided with the source code
 *   in a directory whose title begins with st_images may only be used for internal purposes and
 *   shall not be redistributed to any third party or modified in any way.
 *
 * - Any redistributions in binary form shall not include the capability to display any of the
 *   icons, pictures, logos and other images that are provided with the source code in a directory
 *   whose title begins with st_images.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 * OF SUCH DAMAGE.
 */

package com.st.BlueMS.demos.ActivityRecognition;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.st.BlueSTSDK.Features.FeatureActivity.ActivityType;


/**
 * common class between all the ones that display different activity icons
 */
abstract class ActivityView extends ConstraintLayout{

    /**
     * alpha to use for the selected image
     */
    private static final float SELECTED_ALPHA = 1.0f;

    /**
     * alpha to use for the other images
     */
    private static final float DEFAULT_ALPHA = 0.3f;

    /**
     * currently selected image
     */
    private ImageView mSelectedImage=null;

    public ActivityView(Context context) {
        super(context);
    }

    public ActivityView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ActivityView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * utility method to deselect all the demo icons
     */
    protected void deselectAllImages(){
        for(ActivityType type : ActivityType.values()){
            ImageView image = getSelectedImage(type);
            if(image!=null)
                image.setAlpha(DEFAULT_ALPHA);
        }
    }

    /**
     * map an activity type to the linked imageview
     * @param status current activity
     * @return image view associated to the status activity or null if is an invalid status
     */
    abstract @Nullable
    ImageView getSelectedImage(@NonNull ActivityType status);


    /***
     * select a specific images
     * @param type activity image to select
     */
    public void setActivity(@Nullable ActivityType type) {
        //restore the alpha for the old activity
        if(mSelectedImage !=null)
            mSelectedImage.setAlpha(DEFAULT_ALPHA);

        //find the new activity
        if(type!=null)
            mSelectedImage = getSelectedImage(type);

        //set the alpha for the new activity
        if(mSelectedImage !=null)
            mSelectedImage.setAlpha(SELECTED_ALPHA);
    }

}
