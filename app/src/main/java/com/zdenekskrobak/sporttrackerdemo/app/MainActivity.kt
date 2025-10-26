package com.zdenekskrobak.sporttrackerdemo.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.TrainingDetailScreen
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.TrainingListScreen
import com.zdenekskrobak.sporttrackerdemo.ui.theme.SportTrackerDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SportTrackerDemoTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Route.TrainingList,
                ) {

                    composable<Route.TrainingList> {
                        TrainingListScreen(
                            onCreateTrainingClicked = {
                                navController.navigate(Route.TrainingDetail(it.id, null))
                            },
                            onOpenDetail = { id, source ->
                                navController.navigate(Route.TrainingDetail(id, source))
                            }
                        )
                    }

                    composable<Route.TrainingDetail> { backStackEntry ->
                        val trainingDetail = backStackEntry.toRoute<Route.TrainingDetail>()
                        TrainingDetailScreen(
                            id = trainingDetail.id,
                            dataSource = trainingDetail.source,
                            onNavigateBack = {
                                navController.popBackStack()
                            })
                    }
                }
            }
        }
    }
}
