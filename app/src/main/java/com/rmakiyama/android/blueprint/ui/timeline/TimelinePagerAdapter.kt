package com.rmakiyama.android.blueprint.ui.timeline

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rmakiyama.android.blueprint.ui.article.list.ArticleListFragment

typealias TabItem = Pair<String, Fragment>

internal class TimelinePagerAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var targets: List<TabItem> = listOf()

    override fun getItem(position: Int): Fragment = targets[position].second

    override fun getCount(): Int = targets.size

    override fun getPageTitle(position: Int): CharSequence? {
        return targets[position].first
    }

    // TODO: query to value object
    fun update(queries: List<String>) {
        targets = queries.map { it to ArticleListFragment.newIntent(it) }
    }
}