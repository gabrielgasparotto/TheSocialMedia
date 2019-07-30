package com.example.thesocialmedia.view.mock

interface MockPosts {
    companion object {

        val ERROR = ("{\n"
                + "error: \"Deu ruim irmão!!\"\n"
                + "}")

        val SUCCESS = "[\n" +
                "{\n" +
                "\"userId\": \"1\",\n" +
                "\"id\": \"1\",\n" +
                "\"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                "\"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                "},\n" +
                "{\n" +
                "\"userId\": \"1\",\n" +
                "\"id\": \"2\",\n" +
                "\"title\": \"qui est esse\",\n" +
                "\"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
                "},\n" +
                "{\n" +
                "\"userId\": \"1\",\n" +
                "\"id\": \"3\",\n" +
                "\"title\": \"ea molestias quasi exercitationem repellat qui ipsa sit aut\",\n" +
                "\"body\": \"et iusto sed quo iure\\nvoluptatem occaecati omnis eligendi aut ad\\nvoluptatem doloribus vel accusantium quis pariatur\\nmolestiae porro eius odio et labore et velit aut\"\n" +
                "},\n" +
                "],\n"
    }

}
