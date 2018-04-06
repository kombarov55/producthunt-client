#https://api.producthunt.com/v1
#access_token=591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff

#all-topics
curl https://api.producthunt.com/v1/topics?access_token=591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff | json_pp

#trending-topics
curl "https://api.producthunt.com/v1/topics?search\[trending\]=true&access_token=591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff" | jq '.topics[0] | {name: .name, description: .description, followers_count: .followers_count, image: .image}'

#by-topic
curl "https://api.producthunt.com/v1/posts/all?search\[Tech\]=1&page=1&per_page=3&access_token=591f99547f569b05ba7d8777e2e0824eea16c440292cce1f8dfb3952cc9937ff" | json_pp

{name: .name, thumbnail: .thumbnail.image_url, description: .tagline}


