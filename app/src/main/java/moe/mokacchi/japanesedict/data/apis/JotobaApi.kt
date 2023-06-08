package moe.mokacchi.japanesedict.data.apis

import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.POST

interface JotobaApi {
    @POST("search/words")
    suspend fun searchWords(@Body request: WordsRequest): WordsResponse

    @POST("/search/names")
    suspend fun searchNames(@Body request: NamesRequest): NamesResponse

    @POST("/search/kanji")
    suspend fun searchKanji(@Body request: KanjiRequest): KanjiResponse

    @POST("/search/sentences")
    suspend fun searchSentences(@Body request: SentencesRequest): SentencesResponse
}

data class WordsRequest(
    val query: String,
    val language: String = "English",
    @SerializedName("no_english")
    val noEnglish: Boolean = false,
)

data class NamesRequest(
    val query: String,
    val language: String = "English",
    @SerializedName("no_english")
    val noEnglish: Boolean = false,
)

data class KanjiRequest(
    val query: String,
    val language: String = "English",
    @SerializedName("no_english")
    val noEnglish: Boolean = false,
)

data class SentencesRequest(
    val query: String,
    val language: String = "English",
    @SerializedName("no_english")
    val noEnglish: Boolean = false,
)

data class WordsResponse(
    val kanji: List<Kanji>,
    val words: List<Word>
)

data class NamesResponse(
    val names: List<Name>
)

data class KanjiResponse(
    val kanji: List<Kanji>
)

data class SentencesResponse(
    val sentences: List<Sentence>
)


data class Word(
    val reading: Reading,
    val common: Boolean,
    val senses: List<Sense>,
    val audio: String,
    val pitch: List<Pitch>
)

data class Reading(
    val kana: String,
    val kanji: String,
    val furigana: String
)

data class Sense(
    val glosses: List<String>,
    //val pos: List<String>,
    val language: String
)

data class Pitch(
    val part: String,
    val high: Boolean
)

data class Kanji(
    val literal: String,
    val meanings: List<String>,
    val grade: Int,
    @SerializedName("stroke_count")
    val strokeCount: Int,
    val frequency: Int,
    val jlpt: Int,
    val variant: List<String>,
    val onyomi: List<String>,
    val kunyomi: List<String>,
    val chinese: List<String>,
    val korean_r: List<String>,
    val korean_h: List<String>,
    val parts: List<String>,
    val radical: String,
    @SerializedName("stroke_frames")
    val strokeFrames: String
)

data class Name(
    val kana: String,
    val kanji: String,
    val transcription: String,
    @SerializedName("name_type")
    val nameType: List<String>
)

data class Sentence(
    val content: String,
    val furigana: String,
    val translation: String,
    val language: String
)