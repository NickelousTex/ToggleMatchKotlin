package com.togglematch.ui.matches

import androidx.lifecycle.ViewModel
import com.togglematch.data.model.Match

class MatchesViewModel : ViewModel() {
    
    private val _matches = mutableListOf<Match>()
    val matches: List<Match> get() = _matches
    
    fun addMatch(match: Match) {
        _matches.add(match)
    }
    
    fun removeMatch(matchId: String) {
        _matches.removeAll { it.id == matchId }
    }
    
    fun getMatchById(matchId: String): Match? {
        return _matches.find { it.id == matchId }
    }
}
