package com.app.bank_misr.presentation.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.bank_misr.data.util.cast
import com.app.bank_misr.domain.entity.CurrencySymbolEntity
import com.app.bank_misr.presentation.common.DataState
import com.app.bank_misr.presentation.common.view.ErrorView
import com.app.bank_misr.presentation.common.view.LoadingDialog
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel(),
               navigator: DestinationsNavigator? = null,)
{
  val state by viewModel.state.collectAsState()
  var data by remember { mutableStateOf<List<CurrencySymbolEntity>?>(null) }


  when (state) {
    is DataState.Idle -> {}
    is DataState.Loading -> {
      LoadingDialog()
    }

    is DataState.Success<*> -> data = state.cast<DataState.Success<List<CurrencySymbolEntity>>>().result
    is DataState.Error -> {
      val error = state.cast<DataState.Error>().error.asString()
      ErrorView(message = error) { viewModel.getCurrenciesSymbol() }
    }
  }

  data?.let { MainScreenContent(it, navigator) }
}

@Composable
fun MainScreenContent(symbols:List<CurrencySymbolEntity>,navigator: DestinationsNavigator?)
{

  LazyColumn(){
    itemsIndexed(symbols){_,symbol->
      Text(text = symbol.name)
    }
  }
}