package com.zxcursedsoundboard.apk.feature_sounds_zxcursed.presentation

import androidx.compose.runtime.Composable
import com.zxcursedsoundboard.apk.core.presentation.MainViewModel
import com.zxcursedsoundboard.apk.feature_favourite.presentation.FavouriteViewModel

@Composable
fun ZxcursedSoundScreen(
    mainViewModel: MainViewModel,
    isPlaying: Boolean?,
    favouriteViewModel: FavouriteViewModel,
    currentDestination: String?,
    routeOfPlayingSong: String
) {
//    val favouriteState = favouriteViewModel.favouriteState.collectAsState()
//    val currentPosition = mainViewModel.currentPositionIndex.collectAsState()
//    val context = LocalContext.current
//    LaunchedEffect(key1 = Unit) {
//        mainViewModel.downloadStatus.collect { downloadStatus ->
//            when (downloadStatus) {
//                is DownloadStatus.Success -> {
//                    Toast.makeText(
//                        context,
//                        context.getString(R.string.download_complete_notification_title),
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//                is DownloadStatus.Error -> {
//                    Toast.makeText(
//                        context,
//                        downloadStatus.message,
//                        Toast.LENGTH_LONG
//                    ).show()
//                }
//                else -> {
//                    Unit
//                }
//            }
//        }
//    }
//
//    var expandedIndex by remember { mutableStateOf(-1) }
//
//    val items = remember {
//        mutableStateListOf(
//            MediaItems(R.raw.cursed7, R.string.madmyazel, R.string.zxcursed, R.drawable.madmyazel),
//            MediaItems(
//                R.raw.cursed8,
//                R.string.chtoetoblyat,
//                R.string.zxcursed,
//                R.drawable.chtoetoblyat
//            ),
//        )
//    }
//
//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 108.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//    ) {
//
//        itemsIndexed(items) { index, item ->
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clickable {
////                        mainViewModel.setMedia(
////                            index,
////                            context,
////                            songRes = item.audioResId,
////                            item.songNameRes,
////                            item.songAuthor,
////                            item.imageRes,
////                            items,
////                            currentDestination ?: ""
////                        )
//                    },
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Box(modifier = Modifier.size(64.dp), contentAlignment = Alignment.Center) {
//                    Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.size(64.dp)) {
//                        Image(
//                            painter = painterResource(id = item.imageRes),
//                            contentDescription = null,
//                            modifier = Modifier.size(64.dp),
//                            contentScale = ContentScale.Crop
//                        )
//                    }
//                    //not work with standard AnimatedVisibility
//                    androidx.compose.animation.AnimatedVisibility(
//                        index == currentPosition.value && isPlaying == true && routeOfPlayingSong == currentDestination,
//                        enter = fadeIn(),
//                        exit = fadeOut()
//                    ) {
//                        Icon(
//                            painter = painterResource(R.drawable.outline_pause_24),
//                            contentDescription = "Play/Pause",
//                            modifier = Modifier.size(30.dp)
//                        )
//                    }
//                }
//                Spacer(modifier = Modifier.width(16.dp))
//                Column {
//                    Text(
//                        text = stringResource(id = item.songNameRes),
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                    Text(text = stringResource(id = item.songAuthor), Modifier.alpha(0.5f))
//                }
//                Spacer(modifier = Modifier.weight(1f))
//
//
//                val isFavourite =
//                    favouriteState.value.data?.toString()?.contains(item.songNameRes.toString())
//                        ?: false
//                IconButton(onClick = {
//                    val song = FavouriteEntity(
//                        id=0,
//                        songName = item.songNameRes,
//                        songAuthor = item.songAuthor,
//                        songImageRes = item.imageRes,
//                        item.audioResId
//                    )
//                    if (isFavourite) {
//                        favouriteViewModel.deleteSong(item.songNameRes)
//                    } else {
//                        favouriteViewModel.addSong(song)
//                    }
//                }) {
//                    if (isFavourite) {
//                        Icon(
//                            tint = Color.Red,
//                            modifier = Modifier.size(30.dp),
//                            contentDescription = "favourite",
//                            imageVector = Icons.Outlined.Favorite,
//                        )
//                    } else {
//                        Icon(
//                            modifier = Modifier.size(30.dp),
//                            painter = painterResource(id = R.drawable.baseline_favorite_border_24),
//                            contentDescription = "favourite"
//                        )
//                    }
//                }
//                Box {
//                    IconButton(onClick = {
//                        expandedIndex = index
//                    }) {
//                        Icon(
//                            modifier = Modifier.size(30.dp),
//                            painter = painterResource(id = R.drawable.baseline_more_horiz_24),
//                            contentDescription = "more"
//                        )
//                    }
//                    if (expandedIndex == index) {
//                        DropdownMenu(
//                            expanded = true,
//                            onDismissRequest = { expandedIndex = -1 }
//                        ) {
//                            DropdownMenuItem(
//                                text = { Text(text = stringResource(id = R.string.download)) },
//                                onClick = {
//                                    mainViewModel.downloadRawFile(
//                                        context,
//                                        item.audioResId,
//                                        context.getString(item.songNameRes)
//                                    )
//                                },
//                                leadingIcon = {
//                                    Icon(
//                                        painterResource(id = R.drawable.ic_baseline_download_24),
//                                        contentDescription = null
//                                    )
//                                }
//                            )
//                            DropdownMenuItem(
//                                text = { Text(text = stringResource(id = R.string.share)) },
//                                onClick = {
//                                    mainViewModel.share(
//                                        context = context,
//                                        resourceId = item.audioResId,
//                                        fileName = context.getString(item.songNameRes)
//                                    )
//                                },
//                                leadingIcon = {
//                                    Icon(
//                                        painterResource(id = R.drawable.ic_baseline_share_24),
//                                        contentDescription = null
//                                    )
//                                }
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }


}