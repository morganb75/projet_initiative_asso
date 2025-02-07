const MapTabLikedId = (likes) => {
    return likes.map(item=>item.likedUser.id)
}
export default MapTabLikedId;