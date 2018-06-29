# FlameBarChart

[![](https://jitpack.io/v/dyguests/FlameBarChart.svg)](https://jitpack.io/#dyguests/FlameBarChart)

一个炫酷的柱状图表。

![](./graphics/multi_sample.gif)

# Import

###### Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

###### Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.dyguests:FlameBarChart:x.x.x'
	}

# Usage

参数

    <declare-styleable name="TravelChart">
        <attr name="barWidth" format="dimension"/>
        <attr name="barInterval" format="dimension"/>
        <attr name="barDrawableDefault" format="reference"/>
        <attr name="barDrawablePressed" format="reference"/>
        <attr name="barDrawableFocused" format="reference"/>

        <attr name="barIndicatorDrawable" format="reference"/>
        <attr name="barIndicatorWidth" format="dimension"/>

        <!--顶部提示文字-->
        <attr name="barHintPadding" format="dimension"/>
        <attr name="barHintBackground" format="reference"/>
        <attr name="barHintBackgroundPadding" format="dimension"/>
        <attr name="barHintBackgroundPaddingLeft" format="dimension"/>
        <attr name="barHintBackgroundPaddingTop" format="dimension"/>
        <attr name="barHintBackgroundPaddingRight" format="dimension"/>
        <attr name="barHintBackgroundPaddingBottom" format="dimension"/>
        <attr name="barHintTextSize" format="dimension"/>
        <attr name="barHintTextColor" format="color"/>

        <!--底部x轴显示-->
        <attr name="xAxisPadding" format="dimension"/>
        <attr name="xAxisCurrentBackground" format="reference"/>
        <attr name="xAxisCurrentBackgroundPadding" format="dimension"/>
        <attr name="xLabelTextSize" format="dimension"/>
        <attr name="xLabelTextColor" format="color"/>
        <attr name="xLabelTextColorFocused" format="color"/>
    </declare-styleable>

回调

    onXAxisChangeListeners
        fun onCurrentXAxisChanged(currentXAxis: Int)
        fun onCurrentXAxisOffsetChanged(currentXAxis: Int, currentXAxisOffset: Float, velocity: Float)
        fun oScrollEnd(currentXAxis: Int)

数据结构

        interface IItem {
            /**
             * 获取x轴Label的值
             */
            fun getXLabel(): CharSequence

            /**
             * 获取x轴对应项居中时顶部的提示文字
             */
            fun getXHint(): CharSequence

            /**
             * 获取y轴坐标值
             *
             * 无坐标时 不显示popup框，柱高度当0处理
             */
            fun getYAxis(): Float?
        }

注意：getYAxis 返回null时，不显示数据气泡

# License

Copyright 2018 fanhl

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.