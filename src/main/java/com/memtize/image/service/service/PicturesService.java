package com.memtize.image.service.service;

import com.memtize.image.service.mongo.model.MainPicture;
import com.memtize.image.service.mongo.model.Picture;
import com.memtize.image.service.mongo.repositories.PictureRepositorySupplier;
import com.memtize.image.service.util.ApplicationException;
import com.memtize.image.service.util.ErrorType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PicturesService {


    private final PictureRepositorySupplier pictureRepositorySupplier;

    @Transactional(rollbackFor = Exception.class)
    public void addMainPicture(MainPicture profilePicture) {
        if (!pictureRepositorySupplier
            .getMainPictureRepository().existsById(profilePicture.getId())) {
            throw new ApplicationException(
                ErrorType.ENTITY_NOT_FOUND,
                I18N.getErrorMessage("crypto.notFound", profilePicture.getCryptoId()));
        }
        pictureRepositorySupplier
            .getMainPictureRepository()
            .deleteByCryptoId(profilePicture.getCryptoId());
        pictureRepositorySupplier.getMainPictureRepository().save(profilePicture);
    }

    public List<Picture> getMainPicture(String id, boolean archive) {
        List<Picture> pictures =
            pictureRepositorySupplier.getMainPictureRepository().findAllByCryptoIdAndArchive(id, archive);
        if (pictures.isEmpty()) {
            throw new ApplicationException(
                ErrorType.ENTITY_NOT_FOUND, I18N.getErrorMessage("crypto.mainPictureNotFound", id));
        }
        return pictures;
    }

    public void deleteMainPicture(String id) {
        pictureRepositorySupplier.getMainPictureRepository().deleteByCryptoId(id);
    }
}
