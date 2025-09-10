    package com.janaeswar.AMS.Service;
    import com.mongodb.client.gridfs.model.GridFSFile;
    import org.bson.types.ObjectId;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.mongodb.gridfs.GridFsResource;
    import org.springframework.data.mongodb.gridfs.GridFsTemplate;
    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;
    import org.springframework.data.mongodb.core.query.Query;
    import static org.springframework.data.mongodb.core.query.Criteria.where;

    import java.io.IOException;
    import java.io.InputStream;

    @Service
    public class ImageService {

        @Autowired
        private GridFsTemplate gridFsTemplate;

        // Store image and return its ID
        public String storeImage(MultipartFile file) throws IOException {
            try (InputStream inputStream = file.getInputStream()) {
                ObjectId id = gridFsTemplate.store(inputStream, file.getOriginalFilename(), file.getContentType());
                return id.toHexString();
            }
        }

        // Retrieve image resource by ID
        public GridFsResource getImage(String imageId) throws IOException {
            GridFSFile file = gridFsTemplate.findOne(new Query(where("_id").is(new ObjectId(imageId))));
            if (file == null) {
                return null;
            }
            return gridFsTemplate.getResource(file);
        }
    }

